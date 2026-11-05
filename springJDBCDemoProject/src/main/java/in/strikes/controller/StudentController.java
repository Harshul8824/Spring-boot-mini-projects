package in.strikes.controller;

import in.strikes.model.Student;
import in.strikes.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
         this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Long id){
        return studentService.findById(id);
    }

    @PostMapping
    public int save(@RequestBody Student s){
        return studentService.save(s);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id, @RequestBody Student s){
        return studentService.update(s, id);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Long id){
        return studentService.delete(id);
    }
}
