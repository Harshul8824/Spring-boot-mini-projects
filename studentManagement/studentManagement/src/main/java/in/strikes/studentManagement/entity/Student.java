package in.strikes.studentManagement.entity;

import in.strikes.studentManagement.converter.BooleanToStringConverter;
import in.strikes.studentManagement.embeddable.Address;
import in.strikes.studentManagement.enums.Gender;
import in.strikes.studentManagement.enums.StudentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @Column(precision = 10, scale = 2)
    private BigDecimal fees;

    private LocalDateTime registrationDateTime;

    @Lob
    private byte[] profileDocuments;

    @Convert(converter = BooleanToStringConverter.class)
    @Column(name = "active", length = 1)
    private boolean active;

    @Transient
    private Integer age;

    //permanent address
     @Embedded
     @AttributeOverrides({
             @AttributeOverride(
                name = "street",
                     column = @Column(name = "permanent_address")
             ),
             @AttributeOverride(
                     name = "city",
                     column = @Column(name = "permanent_city")
             ),
             @AttributeOverride(
                     name = "state",
                     column = @Column(name = "permanent_state")
             ),
             @AttributeOverride(
                     name = "pincode",
                     column = @Column(name = "permanent_pincode")
             )
     })
    private Address permanentAddress;

    //present address
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "current_address")
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "current_city")
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column(name = "current_state")
            ),
            @AttributeOverride(
                    name = "pincode",
                    column = @Column(name = "current_pincode")
            )
    })
    private Address currentAddress;

    //collection of phone number
    @ElementCollection
    @CollectionTable(
            name = "student_phone_number",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "phone_number")
    private List<String>phoneNumbers = new ArrayList<>();

    //collection of email addresses
    @ElementCollection
    @CollectionTable(
            name = "student_emails",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "email")
    private List<String>additionalEmails = new ArrayList<>();

    public Student(){}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public byte[] getProfileDocuments() {
        return profileDocuments;
    }

    public void setProfileDocuments(byte[] profileDocuments) {
        this.profileDocuments = profileDocuments;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getAge() {
        if(dateOfBirth == null){
            return null;
        }
        return Period.between(
                dateOfBirth, LocalDate.now()
        ).getYears();
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getAdditionalEmails() {
        return additionalEmails;
    }

    public void setAdditionalEmails(List<String> additionalEmails) {
        this.additionalEmails = additionalEmails;
    }
}
