package com.onlineplatform.admin.utility;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.onlineplatform.admin.db.entities.Course;
import com.onlineplatform.admin.db.entities.Instructor;
import com.onlineplatform.admin.db.entities.Role;
import com.onlineplatform.admin.db.entities.Student;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.db.repo.CourseRepo;
import com.onlineplatform.admin.db.repo.InstructorRepo;
import com.onlineplatform.admin.db.repo.RoleRepo;
import com.onlineplatform.admin.db.repo.StudentRepo;
import com.onlineplatform.admin.db.repo.UserRepo;

public class OperationUtility {
    public static void usersOperations(UserRepo UserRepo) {
        createUsers(UserRepo);
      //  updateUser(UserRepo);
      //  deleteUser(UserRepo);
      //  fetchUsers(UserRepo);
    }

    private static void createUsers(UserRepo UserRepo) {
        User user1 = new User("user1@gmail.com", "pass1");
        UserRepo.save(user1);
        User user2 = new User("user2@gmail.com", "pass2");
        UserRepo.save(user2);
        User user3 = new User("user3@gmail.com", "pass3");
        UserRepo.save(user3);
        User user4 = new User("user4@gmail.com", "pass4");
        UserRepo.save(user4);
    }

    private static void updateUser(UserRepo UserRepo) {
        User user = UserRepo.findById(2L).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        user.setEmail("newEmail@gmail.com");
        UserRepo.save(user);
    }

    private static void deleteUser(UserRepo UserRepo) {
        User user = UserRepo.findById(3L).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        UserRepo.delete(user);
    }

    private static void fetchUsers(UserRepo UserRepo) {
        UserRepo.findAll().forEach(user -> System.out.println(user.toString()));
    }

    public static void rolesOperations(RoleRepo RoleRepo) {
        createRoles(RoleRepo);
     //   updateRole(RoleRepo);
     //   deleteRole(RoleRepo);
     //   fetchRole(RoleRepo);
    }

    private static void createRoles(RoleRepo RoleRepo) {
        Role role1 = new Role("Admin");
        RoleRepo.save(role1);
        Role role2 = new Role("Instructor");
        RoleRepo.save(role2);
        Role role3 = new Role("Student");
        RoleRepo.save(role3);
    }

    private static void updateRole(RoleRepo roleRepo) {
        Role role = roleRepo.findById(1L).orElseThrow(() -> new EntityNotFoundException("Role Not Found"));
        role.setRoleName("NewAdmin");
        roleRepo.save(role);
    }

    private static void deleteRole(RoleRepo RoleRepo) {
        RoleRepo.deleteById(2L);
    }

    private static void fetchRole(RoleRepo RoleRepo) {
        RoleRepo.findAll().forEach(role -> System.out.println(role.toString()));
    }

    public static void assignRolesToUsers(UserRepo UserRepo, RoleRepo roleRepo) {
        Role role = roleRepo.findByRoleName("Admin");
        if (role == null) throw new EntityNotFoundException("Role Not Found");
        List<User> users = UserRepo.findAll();
        users.forEach(user -> {
            user.assignRoleToUser(role);
            UserRepo.save(user);
        });
    }

    public static void instructorsOperations(UserRepo UserRepo, InstructorRepo InstructorRepo, RoleRepo RoleRepo) {
        createInstructors(UserRepo, InstructorRepo, RoleRepo);
//        updateInstructor(InstructorRepo);
//        removeInstructor(InstructorRepo);
//        fetchInstructors(InstructorRepo);
    }

    private static void createInstructors(UserRepo UserRepo, InstructorRepo InstructorRepo, RoleRepo roleRepo) {
        Role role = roleRepo.findByRoleName("Instructor");
        if (role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("instructorUser1@gmail.com", "pass1");
        user1.assignRoleToUser(role);
        UserRepo.save(user1);
        Instructor instructor1 = new Instructor("instructor1FN", "instructor1LN", "Experienced Instructor", user1);
        InstructorRepo.save(instructor1);

        User user2 = new User("instructorUser2@gmail.com", "pass2");
        user2.assignRoleToUser(role);
        UserRepo.save(user2);
        Instructor instructor2 = new Instructor("instructor2FN", "instructor2LN", "Senior Instructor", user2);
        InstructorRepo.save(instructor2);

    }

    private static void updateInstructor(InstructorRepo InstructorRepo) {
        Instructor instructor = InstructorRepo.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));
        instructor.setSummery("Certified Instructor");
        InstructorRepo.save(instructor);
    }

    private static void removeInstructor(InstructorRepo InstructorRepo) {
        InstructorRepo.deleteById(2L);
    }

    private static void fetchInstructors(InstructorRepo InstructorRepo) {
        InstructorRepo.findAll().forEach(instructor -> System.out.println(instructor.toString()));
    }

    public static void studentsOperations(UserRepo UserRepo, StudentRepo StudentRepo, RoleRepo roleRepo) {
        createStudents(UserRepo, StudentRepo, roleRepo);
//        updateStudent(StudentRepo);
//        removeStudent(StudentRepo);
//        fetchStudents(StudentRepo);
    }

    private static void createStudents(UserRepo UserRepo, StudentRepo StudentRepo, RoleRepo roleRepo) {
        Role role = roleRepo.findByRoleName("Student");
        if (role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("stdUser1@gmail.com", "pass1");
        user1.assignRoleToUser(role);
        UserRepo.save(user1);
        Student student1 = new Student("student1FN", "student1LN", "master", user1);
        StudentRepo.save(student1);

        User user2 = new User("stdUser2@gmail.com", "pass2");
        user2.assignRoleToUser(role);
        UserRepo.save(user2);
        Student student2 = new Student("student2FN", "student2LN", "Phd", user2);
        StudentRepo.save(student2);
    }

    private static void updateStudent(StudentRepo StudentRepo) {
        Student student = StudentRepo.findById(2L).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        student.setFirstName("updatedStdFN");
        student.setLastName("updatedStdLN");
        StudentRepo.save(student);
    }


    private static void removeStudent(StudentRepo StudentRepo) {
        StudentRepo.deleteById(2L);
    }

    private static void fetchStudents(StudentRepo StudentRepo) {
        StudentRepo.findAll().forEach(student -> System.out.println(student.toString()));
    }

    public static void coursesOperations(CourseRepo CourseRepo, InstructorRepo InstructorRepo, StudentRepo StudentRepo) {
       // createCourses(CourseRepo, InstructorRepo);
     //   updateCourse(CourseRepo);
     //   deleteCourse(CourseRepo);
     //   fetchCourses(CourseRepo);
     //   assignStudentsToCourse(CourseRepo, StudentRepo);
        fetchCoursesForStudent(CourseRepo);
    }


    private static void createCourses(CourseRepo CourseRepo, InstructorRepo InstructorRepo) {
        Instructor instructor = InstructorRepo.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));

        Course course1 = new Course("Hibernate", "5 Hours", "Introduction to Hibernate", instructor);
        CourseRepo.save(course1);
        Course course2 = new Course("Spring Data JPA", "10 Hours", "Master Spring Data JPA", instructor);
        CourseRepo.save(course2);
    }

    private static void updateCourse(CourseRepo CourseRepo) {
        Course course = CourseRepo.findById(1L).orElseThrow(() -> new EntityNotFoundException("Course Not Found"));
        course.setCourseDuration("20 Hours");
        CourseRepo.save(course);
    }

    private static void deleteCourse(CourseRepo CourseRepo) {
        CourseRepo.deleteById(2L);
    }

    private static void fetchCourses(CourseRepo CourseRepo) {
        CourseRepo.findAll().forEach(course -> System.out.println(course.toString()));
    }

    private static void assignStudentsToCourse(CourseRepo CourseRepo, StudentRepo StudentRepo) {
        Optional<Student> student1 = StudentRepo.findById(1L);
        Optional<Student> student2 = StudentRepo.findById(2L);
        Course course = CourseRepo.findById(1L).orElseThrow(() -> new EntityNotFoundException("Course Not Found"));

        student1.ifPresent(course::assignStudentToCourse);
        student2.ifPresent(course::assignStudentToCourse);
        CourseRepo.save(course);
    }


    private static void fetchCoursesForStudent(CourseRepo CourseRepo) {
        CourseRepo.getCoursesByStudentId(1L).forEach(course -> System.out.println(course.toString()));
    }

}