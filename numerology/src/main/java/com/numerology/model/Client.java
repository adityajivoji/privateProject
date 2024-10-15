package com.numerology.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

	private Date dob;
    private String gender;
    private Integer kua;
    private Integer driver;
    private Integer conductor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    // Getters, setters, constructors
    
    
    // Constructor
    public Client() {
    	
    }
    public Client(String name, String dobInput, String gender, User user) {
        this.name = name;
        this.gender = gender;

        // Parsing the date of birth from the string input
        Date dob = parseDateOfBirth(dobInput);
        if (dob == null) {
            throw new IllegalArgumentException("Invalid date format. Please use DD/MM/YYYY.");
        }
        

        this.dob = dob;

        // Extract day, month, and year components from the Date object
        Calendar cal = Calendar.getInstance();
        cal.setTime(dob);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;  // Calendar.MONTH is 0-based
        int year = cal.get(Calendar.YEAR);

        // Calculate Driver (D), Conductor (C), and Kua (K) values
        this.driver = reduceToSingleDigit(day);
        this.conductor = reduceToSingleDigit(Integer.parseInt(String.format("%02d%02d%04d", day, month, year)));

        int yearReduced = reduceToSingleDigit(year);
        if (gender.equalsIgnoreCase("male")) {
            this.kua = reduceToSingleDigit(11 - yearReduced);
        } else if (gender.equalsIgnoreCase("female")) {
            this.kua = reduceToSingleDigit(4 + yearReduced);
        } else {
            throw new IllegalArgumentException("Invalid gender. Please enter 'male' or 'female'.");
        }
        this.user = user;
    }

    // Method to reduce a number to a single digit
    private int reduceToSingleDigit(int number) {
        while (number > 9) {
            number = number / 10 + number % 10;
        }
        return number;
    }

    // Method to parse date from DD/MM/YYYY format to java.util.Date
    private Date parseDateOfBirth(String dobInput) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(dobInput, formatter);
            return java.sql.Date.valueOf(localDate);
        } catch (DateTimeParseException e) {
            return null;  // return null if date parsing fails
        }
    }
    

    
    public Integer getDriver() {
		return driver;
	}

	public void setDriver(Integer driver) {
		this.driver = driver;
	}


	public Integer getConductor() {
		return conductor;
	}

	public void setConductor(Integer conductor) {
		this.conductor = conductor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getKua() {
		return kua;
	}

	public void setKua(Integer kua) {
		this.kua = kua;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
