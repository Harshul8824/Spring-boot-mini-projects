package in.strikes.service;

import in.strikes.model.Student;
import in.strikes.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findById(Long id){
        return studentRepository.findById(id);
    }

    public int save(Student s){
        return studentRepository.save(s);
    }

    public int update(Student s, Long id){
        return studentRepository.update(s, id);
    }

    public int delete(Long id){
        return studentRepository.delete(id);
    }
}
