package com.numerology.dto;

public class ClientDto {
    private Long id;
    private String name;
	private String dob;
    private String gender;
    private Integer kua;
    private Integer driver;
    private Integer conductor;

    // Getters and Setters
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

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
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
}
