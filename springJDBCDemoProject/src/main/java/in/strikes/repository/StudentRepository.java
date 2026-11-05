package in.strikes.repository;

import in.strikes.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
   private final JdbcTemplate jdbc;

   public StudentRepository(JdbcTemplate jdbcTemplate){
       this.jdbc = jdbcTemplate;
   }

   private final RowMapper<Student>mapper = (rs, row) ->
           new Student(
                   rs.getLong("id"),
                   rs.getString("name"),
                   rs.getString("email"),
                   rs.getInt("age")
           );

   public List<Student> findAll(){
       return jdbc.query(
               "select id, name, email, age from students",
               mapper
       );
   }
   public Student findById(Long id){
       return jdbc.queryForObject(
               "select id, name, email, age from students where id = ?",
               mapper,
               id
       );
   }

   public int save(Student s){
      return jdbc.update(
              "insert into students(name, email, age) values(?, ?, ?)",
              s.name(), s.email(), s.age()
      );
   }

   public int update(Student s, Long id){
       return jdbc.update(
               "update students set name=?, email=?, age=? where id = ?",
               s.name(), s.email(), s.age(),
               id
       );
   }

   public int delete(Long id){
       return jdbc.update(
               "delete from students where id=?",
               id
       );
   }
}
