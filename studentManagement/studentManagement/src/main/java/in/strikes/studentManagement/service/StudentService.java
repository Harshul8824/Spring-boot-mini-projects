package in.strikes.studentManagement.service;

import in.strikes.studentManagement.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentService {

    @PersistenceContext
    private EntityManager entityManager;

   //create
    public Student createStudent(Student student){
        entityManager.persist(student);
        return student;
    }

    //getstudent
    public Student getStudent(Long id){
        return entityManager.find(Student.class, id);
    }

    //getall student
    public List<Student> getAllStudent(){
        return entityManager.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    //update student using dirty checking
    public Student updateStudent(Student updatedStudent, Long id){
        Student student = entityManager.find(Student.class, id);
        if(student == null){
            return null;
        }

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setFees(updatedStudent.getFees());
        student.setStatus(updatedStudent.getStatus());
        student.setCurrentAddress(updatedStudent.getCurrentAddress());
        student.setPermanentAddress(updatedStudent.getPermanentAddress());

        return student;
    }

    //delete student
    public Boolean deleteStudent(Long id){
        Student student = entityManager.find(Student.class, id);
        if(student == null){
            return false;
        }

        entityManager.remove(student);

        return true;
    }
}
