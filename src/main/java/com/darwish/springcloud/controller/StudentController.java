package com.darwish.springcloud.controller;

import com.darwish.springcloud.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("students")
public class StudentController {

    private static ArrayList<Student> studentList = new ArrayList<>();

    {
        studentList.add(new Student(1, "Cristaino", "Ronaldo"));
        studentList.add(new Student(2, "Xavi", "Hernandes"));
        studentList.add(new Student(3, "Deago", "Alba"));
    }

    // get student
    // http://localhost:8080/student
    @GetMapping(path = "student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "Moh", "Darwish");
//        return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok(student);
    }

    // get students
    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1, "Cristaino", "Ronaldo"));
        students.add(new Student(2, "Xavi", "Hernandes"));
        students.add(new Student(3, "Deago", "Alba"));
        return ResponseEntity.ok(students);
    }

    // get student
    // http://localhost:8080/student/1/moh/darwish
    @GetMapping(path = "{id}/{firstName}/{lastName}")
    public ResponseEntity<Student> getStudentWithPathVariable(@PathVariable int id,
                                                              @PathVariable String firstName,
                                                              @PathVariable String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok().header("custom", "dummy").body(student);
    }

    // get student
    // http://localhost:8080/students/query?id=1
    @GetMapping(path = "query")
    public ResponseEntity<Student> getStudentRequestParam(@RequestParam int id,
                                                          @RequestParam String firstName,
                                                          @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // create student
    // http://localhost:8080/students/create
    @PostMapping(path = "create")
    public ResponseEntity<Student> createStudentRequestBody(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // update student
    // http://localhost:8080/students/1
    @PutMapping(path = "{id}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,
                                                 @PathVariable int id) {
        try {
            Student student1 = studentList.stream().filter(x -> x.getId() == id).findFirst().get();
            studentList.remove(student1);
        } catch (Exception e) {
            System.out.println("Error finding student entity");
        }
        Student student2 = new Student(id, student.getFirstName(), student.getLastName());
        studentList.add(student2);
        return ResponseEntity.ok(student2);
    }

    // delete student
    // http://localhost:8080/students/1
    @DeleteMapping(path = "{id}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        Student student1 = studentList.stream().filter(x -> x.getId() == id).findFirst().get();
        studentList.remove(student1);

        return ResponseEntity.ok(student1);
    }

}
