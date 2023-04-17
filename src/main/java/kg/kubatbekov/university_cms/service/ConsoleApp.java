package kg.kubatbekov.university_cms.service;

import kg.kubatbekov.university_cms.dao.*;
import kg.kubatbekov.university_cms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);

    private final RoomDAO roomDAO;
    private final TimeslotDAO timeslotDAO;
    private final ProfessorDAO professorDAO;
    private final SubjectDAO subjectDAO;
    private final GroupDAO groupDAO;
    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;

    @Autowired
    public ConsoleApp(
            RoomDAO roomDAO,
            TimeslotDAO timeslotDAO,
            ProfessorDAO professorDAO,
            SubjectDAO subjectDAO,
            GroupDAO groupDAO,
            CourseDAO courseDAO,
            StudentDAO studentDAO) {
        this.roomDAO = roomDAO;
        this.timeslotDAO = timeslotDAO;
        this.professorDAO = professorDAO;
        this.subjectDAO = subjectDAO;
        this.groupDAO = groupDAO;
        this.courseDAO = courseDAO;
        this.studentDAO = studentDAO;
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome!");
        System.out.println("This is timetable console application.");
        runConsole();
    }

    private void runConsole() {
        runTimetable();
        boolean cycle = true;

        while (cycle) {
            String text = """
                    What would you like to do?
                    1. See student's timetable
                    2. See teacher's timetable
                    3. Stop app
                    """;
            System.out.println(text);
            int command = scanner.nextInt();
            if (command == 1) {
                getStudentTimetable();
            } else if (command == 2) {
                getTeacherTimetable();
            } else if (command == 3) {
                cycle = false;
                scanner.close();
            } else {
                System.out.println("Wrong command! Try again!");
                runConsole();
            }
        }
    }

    private void getTeacherTimetable() {
        System.out.println("Enter teacher's id: ");
        int teacherId = scanner.nextInt();
        Optional<Professor> professor = professorDAO.findById(teacherId);
        if (professor.isEmpty()) {
            System.out.println("Wrong id, try again!");
            getTeacherTimetable();
        } else {
            List<Course> courses = courseDAO.findByProfessorId(professor.get().getProfessorId());
            System.out.println(" Teacher's timetable for week");
            printTimetable(courses);
            calculateAndDrawTimetable(courses);
        }
    }

    private void getStudentTimetable() {
        System.out.println("Enter student's id: ");
        int studentId = scanner.nextInt();
        Optional<Student> student = studentDAO.findById(studentId);
        if (student.isEmpty()) {
            System.out.println("Wrong id, try again!");
            getStudentTimetable();
        } else {
            int groupId = student.get().getGroup().getGroupId();
            List<Course> courses = courseDAO.findByGroupId(groupId);
            System.out.println(" Student's timetable for week");
            printTimetable(courses);
            calculateAndDrawTimetable(courses);
        }
    }

    private void calculateAndDrawTimetable(List<Course> courses) {
        String[][] timetable = sortTimetableToArray(courses);

        String title = getDaysInLine();
        String all = drawTimetable(timetable);

        System.out.println(title);
        System.out.println(all);
    }

    private String drawTimetable(String[][] timetable) {
        Timeslot.Weekday[] weekdays = Timeslot.getWeekDays();
        Timeslot.Duration[] durations = Timeslot.getDuration();

        int sizeOfWeekDays = weekdays.length;
        int sizeOfDuration = durations.length;

        List<String> durationStr = Timeslot.getDurationAsList();

        StringBuilder all = new StringBuilder();
        for (int i = 0; sizeOfDuration > i; i++) {
            String baseLeft = "%-15s| ";
            String baseRight = "%15s| ";
            StringBuilder line = new StringBuilder();
            line.append(String.format(baseRight, durationStr.get(i)));
            for (int j = 0; sizeOfWeekDays > j; j++) {
                if (timetable[i][j] != null) {
                    line.append(String.format(baseLeft, timetable[i][j]));
                } else {
                    line.append(String.format(baseLeft, " "));
                }
            }
            line.append("\n");
            all.append(line);
        }
        return all.toString();
    }

    private String[][] sortTimetableToArray(List<Course> courses) {
        Timeslot.Weekday[] weekdays = Timeslot.getWeekDays();
        Timeslot.Duration[] durations = Timeslot.getDuration();

        int sizeOfWeekDays = weekdays.length;
        int sizeOfDuration = durations.length;

        String[][] timetable = new String[sizeOfDuration][sizeOfWeekDays];

        int durationIndex = 0;
        for (Timeslot.Duration duration : durations) {
            int weekdayIndex = 0;
            for (Timeslot.Weekday weekday : weekdays) {
                for (Course course : courses) {
                    if (Objects.equals(course.getTimeslot().getDurationValue(), duration.getHours())
                            && course.getTimeslot().getWeekday() == weekday) {
                        timetable[durationIndex][weekdayIndex] = "Course id - " + course.getCourseId();
                    }
                }
                weekdayIndex++;
            }
            durationIndex++;
        }

        return timetable;
    }

    private String getDaysInLine() {
        Timeslot.Weekday[] weekdays = Timeslot.getWeekDays();

        String newLine = "%-15s| ";
        StringBuilder line = new StringBuilder();

        line.append(String.format(newLine, " "));
        for (Timeslot.Weekday weekday : weekdays) {
            line.append(String.format(newLine, weekday.toString().toUpperCase()));
        }
        line.append("\n");

        line.append(String.format(newLine, " "));
        line.append(String.valueOf(String.format(newLine, "-".repeat(15))).repeat(weekdays.length));
        return line.toString();
    }

    private void runTimetable() {
        final int SIZE_OF_SUBJECTS_PROFESSORS = 13;
        final int SIZE_OF_GROUPS_SUBJECTS = 26;

        int DB_SIZE_OF_SUBJECTS_PROFESSORS = subjectDAO.subjectsProfessorsSize();
        int DB_SIZE_OF_GROUPS_SUBJECTS = groupDAO.groupsSubjectsSize();
        int DB_COURSES_SIZE = courseDAO.findAll().size();

        if (DB_COURSES_SIZE == 0) {
            List<Course> timetable = generateTimetable();
            courseDAO.saveAll(timetable);
            printTimetable(timetable);
        } else if (
                DB_SIZE_OF_SUBJECTS_PROFESSORS != SIZE_OF_SUBJECTS_PROFESSORS
                        && DB_SIZE_OF_GROUPS_SUBJECTS != SIZE_OF_GROUPS_SUBJECTS) {
            courseDAO.deleteAll();
            List<Course> newTimetable = generateTimetable();
            courseDAO.saveAll(newTimetable);
            printTimetable(newTimetable);
        }
    }

    private List<Course> generateTimetable() {
        // Get a Timetable object with all the available information.
        Timetable timetable = initializeTimetable();

        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

        // Initialize population
        Population population = ga.initializePopulation(timetable);

        // Evaluate population
        ga.calculateFitnessOfSolutionInPopulation(population, timetable);

        // Keep track of current generation
        int generation = 1;

        // Start evolution loop
        while (!ga.isTerminationConditionMet(generation, 1000)
                && !ga.isTerminationConditionMet(population)) {
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // Evaluate population
            ga.calculateFitnessOfSolutionInPopulation(population, timetable);
        }
        Solution bestSolution = population.getSolutionOfSortedPopulation(0);
        timetable.createCourses(bestSolution);

        return timetable.getCourses();
    }

    private void printTimetable(List<Course> courses) {
        String newLine = "%-20s|";
        StringBuilder info = new StringBuilder();

        System.out.println();
        for (Course course : courses) {
            info.append(String.format(newLine, "|Course id")).append(String.format(newLine, course.getCourseId())).append("\n");
            info.append(String.format(newLine, "|Subject")).append(String.format(newLine, course.getSubject().getSubjectName())).append("\n");
            info.append(String.format(newLine, "|Group")).append(String.format(newLine, course.getGroup().getGroupName())).append("\n");
            info.append(String.format(newLine, "|Room")).append(String.format(newLine, course.getRoom().getRoomNumber())).append("\n");
            info.append(String.format(newLine, "|Professor")).append(String.format(newLine, course.getProfessor().getProfessorName())).append("\n");
            info.append(String.format(newLine, "|Day")).append(String.format(newLine, course.getTimeslot().getWeekday().toString().toUpperCase())).append("\n");
            info.append(String.format(newLine, "|Time")).append(String.format(newLine, course.getTimeslot().getDurationValue())).append("\n");
            info.append(String.format(newLine, "-".repeat(41))).append("\n");
        }
        System.out.println(info);
    }

    private Timetable initializeTimetable() {
        Timetable timetable = new Timetable();

        List<Room> rooms = roomDAO.getAll();
        // Set up rooms
        timetable.setRooms(rooms);

        List<Timeslot> timeslots = timeslotDAO.findAll();
        // Set up timeslots
        timetable.setTimeslots(timeslots);

        List<Professor> professors = professorDAO.getAll();
        // Set up professors
        timetable.setProfessors(professors);

        List<Subject> subjects = subjectDAO.getAll();
        // Set up modules and define the professors that teach them
        timetable.setSubjects(subjects);

        List<Group> groups = groupDAO.getAll();
        // Set up student groups and the modules they take.
        timetable.setGroups(groups);

        return timetable;
    }
}
