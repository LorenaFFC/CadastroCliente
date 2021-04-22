package com.bank.BankTransaction.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotEmpty(message = "O campo Name não pode ser NULO")
    private String Name;
    @NotEmpty(message = "O campo LastName não pode ser NULO")
    private String LastName;
    @NotEmpty(message = "O campo Email não pode ser NULO")
    @Email(message = "Email Inválido")
    private String Email;
    @NotEmpty(message = "O campo CNH não pode ser NULO")
    private String Cnh;


    public Cliente() {
    }

    public Cliente(Long id, String name, String lastName, String email, String cnh) {
        Id = id;
        Name = name;
        LastName = lastName;
        Email = email;
        Cnh = cnh;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCnh() {
        return Cnh;
    }

    public void setCnh(String cnh) {
        Cnh = cnh;
    }
}
