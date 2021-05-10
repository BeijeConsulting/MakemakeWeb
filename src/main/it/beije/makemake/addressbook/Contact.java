package it.beije.makemake.addressbook;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.persistence.*;

import java.sql.*;
import java.util.Locale;

@Entity
@Table(name="rubrica")
public class Contact implements Comparable<Contact> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="nome")
    private String name;
    @Column(name="cognome")
    private String surname;
    @Column(name="telefono")
    private String phone;
    @Column(name="email")
    private String mail;


    public Contact() {

    }


    public Contact(String[] fields) {
        name = fields[0];
        surname = fields[1];
        phone = fields[2];
        mail = fields[3];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Contact(int id, String name, String surname, String phone, String mail) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
    }

    public Contact(String name, String surname, String phone, String mail) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
    }


    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Contact(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }


    public static Contact parseContact(String contactString, String delim, boolean useQuotes) {
        String[] fields = contactString.split(delim);
        if (useQuotes) {
            for (int i = 0; i < fields.length; i++) {
                fields[i] = removeQuotes(fields[i]);
            }
        }
        String name = fields[0];
        String surname = fields[1];
        String phone = fields[2];
        String mail = fields[3];

        return new Contact(name, surname, phone, mail);
    }


    private static String removeQuotes(String field) {
        return field.substring(1, field.length()-1);
    }

    public String format(String delim) {
        StringBuilder result = new StringBuilder();
        result.append(name).append(delim)
                .append(surname).append(delim)
                .append(phone).append(delim)
                .append(mail).append(delim)
                .append("\n");
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + name);
        sb.append(" Surname: " + surname);
        sb.append(" Phone: " + phone);
        sb.append(" Mail: " + mail);
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public int compareTo(Contact o) {
        int r = 0;
        if (bothStringsDefined(this.name, o.name)) {
            r = this.name.toLowerCase(Locale.ROOT).compareTo(o.name.toLowerCase(Locale.ROOT));
            if (r != 0) return r;
        }
        if (bothStringsDefined(this.surname, o.surname)) {
            r = this.surname.toLowerCase(Locale.ROOT).compareTo(o.surname.toLowerCase(Locale.ROOT));
            if (r != 0) return r;
        }
        if (bothStringsDefined(this.phone, o.phone)) {
            r = this.phone.toLowerCase(Locale.ROOT).compareTo(o.phone.toLowerCase(Locale.ROOT));
            if (r != 0) return r;
        }
        if (bothStringsDefined(this.mail, o.mail)) {
            r = this.mail.toLowerCase(Locale.ROOT).compareTo(o.mail.toLowerCase(Locale.ROOT));
            if (r != 0) return r;
        }
        return r;
    }

    private static boolean bothStringsDefined(String s1, String s2) {
        return s1 != null && !s1.isEmpty() && s2 != null && !s2.isEmpty();
    }

    public Element getXMLElement(Document document) throws Exception {
        Element contact = document.createElement("contatto");
        Element name = document.createElement("nome");
        Element surname = document.createElement("cognome");
        Element phone = document.createElement("telefono");
        Element mail = document.createElement("mail");
        name.setTextContent(this.name);
        surname.setTextContent(this.surname);
        phone.setTextContent(this.phone);
        mail.setTextContent(this.mail);
        contact.appendChild(name);
        contact.appendChild(surname);
        contact.appendChild(phone);
        contact.appendChild(mail);
        return contact;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contact) {
            Contact c = (Contact)obj;
            return this.compareTo(c) == 0;
        }
        return false;
    }

    public void insertIntoDatabase(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT into rubrica (nome, cognome, telefono, email) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, mail);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static Contact createFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("nome");
        String surname = resultSet.getString("cognome");
        String phone = resultSet.getString("telefono");
        String mail = resultSet.getString("email");
        return new Contact(name, surname, phone, mail);
    }
}