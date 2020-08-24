package com.Products.Model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="TB_Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String Name;

    @NotBlank
    private String tags;

    @NotBlank
    private String CPF;

    @NotBlank
    private String password;

    public String getCPF() { return CPF; }

    public void setCPF(String CPF) {this.CPF = CPF; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return Name; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) { Name = name; }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
