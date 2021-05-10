package it.beije.makemake.addressbook;

import it.beije.makemake.database.JPAManager;
import it.beije.makemake.database.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressBook {

    private List<Contact> contactList = new ArrayList<>();
    private Contact header = new Contact("NOME", "COGNOME", "TELEFONO", "EMAIL");

    public List<Contact> getContactList() {
        return contactList;
    }


    public static AddressBook createFromCSV(String path, String delim, boolean useQuotes) throws Exception {
        AddressBook addressBook = new AddressBook();
        FileReader fileReader = null;
        fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        addressBook.header = Contact.parseContact(bufferedReader.readLine(), delim, useQuotes);
        while (bufferedReader.ready()) {
            String row = bufferedReader.readLine();
            Contact c = Contact.parseContact(row, delim, useQuotes);
            addressBook.contactList.add(c);
        }
        fileReader.close();
        return addressBook;
    }

    public static AddressBook createFromXML(String path) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {

        AddressBook result = new AddressBook();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("Il file specificato non esiste!");
        }
        Document document = documentBuilder.parse(file);
        Element addressBook = document.getDocumentElement();
        NodeList contacts = addressBook.getElementsByTagName("contatto");

        for (int i = 0; i < contacts.getLength(); i++) {
            Element contact = (Element)contacts.item(i);

            Element name = (Element)contact.getElementsByTagName("nome").item(0);
            Element surname = (Element)contact.getElementsByTagName("cognome").item(0);
            Element phone = (Element)contact.getElementsByTagName("telefono").item(0);
            Element mail = (Element)contact.getElementsByTagName("email").item(0);

            Contact c1;
            if (mail != null) {
                c1 = new Contact(name.getTextContent(),
                        surname.getTextContent(),
                        phone.getTextContent(),
                        mail.getTextContent());
            } else {
                c1 = new Contact(name.getTextContent(),
                        surname.getTextContent(),
                        phone.getTextContent());
            }

            result.contactList.add(c1);

        }

        return result;

    }

    public void toXMLFile(String destPath) throws Exception {
        FileWriter fileWriter = new FileWriter(destPath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element root = document.createElement("contatti");
        for (Contact contact :
                contactList) {
            Element contactElement = contact.getXMLElement(document);
            root.appendChild(contactElement);
        }
        document.appendChild(root);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);

        StreamResult result = new StreamResult(new File(destPath));

        transformer.transform(domSource, result);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contact contact :
                contactList) {
            sb.append(contact.toString());
        }
        return sb.toString();
    }

    public String format(String delim) {
        StringBuilder result = new StringBuilder();
        result.append(header.format(delim));
        for (Contact contact :
                contactList) {
            result.append(contact.format(delim));
        }
        return result.toString();
    }

    public void toFile(String destPath, String delim) throws IOException {
        File file = new File(destPath);
        FileWriter fileWriter = new FileWriter(file);
        String content = format(delim);
        fileWriter.write(content);
        fileWriter.close();
    }

    public AddressBook addContact(Contact contact) {
        contactList.add(contact);
        return this;
    }

    public AddressBook addContact(String contactString, String delim, boolean useQuotes) {
        Contact c = Contact.parseContact(contactString, delim, useQuotes);
        addContact(c);
        return this;
    }

    public AddressBook addContact(String[] fields) {
        Contact c = new Contact(fields);
        addContact(c);
        return this;
    }

    public AddressBook merge(AddressBook addressBook) {
        for (Contact c :
                addressBook.contactList) {
            this.addContact(c);
        }
        return this;
    }

    public AddressBook merge(String filePath, String delim, boolean useQuotes) throws Exception {
        AddressBook newBook = AddressBook.createFromCSV(filePath, delim, useQuotes);
        return this.merge(newBook);
    }

    public AddressBook sort() {
        Collections.sort(contactList);
        return this;
    }

    public List<Contact> search(Contact contact) {
        List<Contact> result = new ArrayList<>();
        sort();
        int i = Collections.binarySearch(contactList, contact);
        if (i < 0)
            return result;
        int k = i;
        while(k >= 0 && contactList.get(k).compareTo(contact)==0)
            result.add(contactList.get(k--));
        k = i+1;
        while(k < contactList.size() && contactList.get(k).compareTo(contact)==0)
            result.add(contactList.get(k++));
        return result;
    }

    public List<Contact> search(String name) {
        Contact c = new Contact(name);
        return search(c);
    }

    public boolean remove(Contact c) {
        return contactList.remove(c);
    }



    public void insertIntoDatabase(String url, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,
                    user, password);
            for (Contact contact :
                    contactList) {
                contact.insertIntoDatabase(connection);
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static AddressBook createFromDatabase(String url, String user, String password) {
        AddressBook addressBook = new AddressBook();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,
                    user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rubrica");
            while (resultSet.next()) {
                addressBook.contactList.add(Contact.createFromResultSet(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return addressBook;
    }

    public void fetchFromDatabase(String url, String user, String password) {
        //similar to the above method but add to existing AddressBook
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,
                    user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rubrica");
            while (resultSet.next()) {
                contactList.add(Contact.createFromResultSet(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoDatabase2() {
        //using hibernate instead of jdbc directly
        SessionManager sessionManager = SessionManager.getSessionManager();
        Session session = sessionManager.getSession();
        for (Contact c :
                contactList) {
            Transaction transaction = session.beginTransaction();
            session.save(c);
            transaction.commit();

        }
        sessionManager.closeSession(session);

    }

    public static AddressBook createFromDatabase2() {
        //using hibernate instead of jdbc directly
        AddressBook addressBook = new AddressBook();
        SessionManager sessionManager = SessionManager.getSessionManager();
        Session session = sessionManager.getSession();
        List<Contact> contacts = session.createCriteria(Contact.class).list();
        for (Contact c :
                contacts) {
            addressBook.addContact(c);
        }
        sessionManager.closeSession(session);
        return addressBook;

    }

    public static AddressBook createFromDatabaseJPA() {
        //version using JPA
        AddressBook addressBook = new AddressBook();
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        Query contacts = entityManager.createQuery("SELECT c FROM Contact as c");
        List<Contact> contactList = contacts.getResultList();
        for (Contact c :
                contactList) {
            addressBook.addContact(c);
        }
        jpaManager.closeEntityManager(entityManager);
        return addressBook;
    }




    public void updateDB() {
        //can be called only if AddressBook is created from db
        //or if it has been uploaded to db
        //(Now using JPA instead of Hibernate)
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        for (Contact c :
                contactList) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(c);
            entityTransaction.commit();
        }
        jpaManager.closeEntityManager(entityManager);
    }


    public void updateNameAndDB(String oldName, String newName) {
        //can be called only if AddressBook is created from db
        //or if it has been uploaded to db
        Contact c = new Contact();
        c.setName(oldName);
        List<Contact> updated = search(c);
        SessionManager sessionManager = SessionManager.getSessionManager();
        Session session = sessionManager.getSession();
        for (Contact contact:
                updated) {
            contact.setName(newName);
            Transaction transaction = session.beginTransaction();
            session.update(contact);
            transaction.commit();
        }
        sessionManager.closeSession(session);
    }

    public void updateNameAndDBJPA(String oldName, String newName) {
        //can be called only if AddressBook is created from db
        //or if it has been uploaded to db
        //(Using JPA instead of Hibernate)
        Contact c = new Contact();
        c.setName(oldName);
        List<Contact> updated = search(c);
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        for (Contact contact:
                updated) {
            contact.setName(newName);
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(contact);
            entityTransaction.commit();
        }
        jpaManager.closeEntityManager(entityManager);
    }


    public boolean removeAllAndUpdateDB(Contact c) {
        //can be called only if AddressBook is created from db
        //or if it has been uploaded to db.
        //Note that the object passed is only used for testing equality
        //(you are not obligated to pass the entity bean corresponding to a database row).
        //This removes ALL the objects satisfying equality
        //from AddressBook and database
        //Return true if at least one contact was removed
        //(Now using JPA instead of Hibernate)
        boolean removed = false;
        List<Contact> updated = search(c);
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        for (Contact contact:
                updated) {
            removed = contactList.remove(contact);
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            //we can't write
            //entityManager.remove(contact) because it's detached now
            //A "clean" solution is thus to use the id
            entityManager.remove(entityManager.find(Contact.class, contact.getId()));
            entityTransaction.commit();
        }
        jpaManager.closeEntityManager(entityManager);
        return removed;
    }

    public boolean removeAllAndUpdateDB(String name) {
        //remove by name
        Contact contact = new Contact();
        contact.setName(name);
        return removeAllAndUpdateDB(contact);
    }


    public static AddressBook getContactsFromNameList(List<String> names) {
        //Using Hibernate Criteria Query Language (deprecated)
        AddressBook addressBook = new AddressBook();
        SessionManager sessionManager = SessionManager.getSessionManager();
        Session session = sessionManager.getSession();
        Criteria criteria = session.createCriteria(Contact.class)
                .add(Restrictions.in("name", names));
        addressBook.contactList = criteria.list();
        sessionManager.closeSession(session);
        return addressBook;
    }





}