package in.strikes.studentManagement.controller;

import in.strikes.studentManagement.entity.Student;
import in.strikes.studentManagement.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //create
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    //get
    @GetMapping("/{id}")
    public ResponseEntity<Student>getStudent(@PathVariable Long id){
        Student student = studentService.getStudent(id);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    //update
    @PutMapping("/{id}")
     public ResponseEntity<Student>updateStudent(@RequestBody Student updatedStudent, @PathVariable Long id){
        Student student = studentService.updateStudent(updatedStudent, id);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
     }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeStudent(@PathVariable Long id){
        Boolean deleted = studentService.deleteStudent(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
