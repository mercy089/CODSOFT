package studentmanegamnt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementSystemUI extends JFrame {
    private JTextField nameTextField, rollNumberTextField, gradeTextField;
    private JTextArea outputTextArea;
    private StudentManagementSystem sms;

    public StudentManagementSystemUI() {
        sms = new StudentManagementSystem();

        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberTextField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        gradeTextField = new JTextField();

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberTextField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeTextField);
        inputPanel.add(addButton);
        inputPanel.add(searchButton);

        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(displayButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addStudent() {
        String name = nameTextField.getText();
        String rollNumberStr = rollNumberTextField.getText();
        String grade = gradeTextField.getText();

        if (name.isEmpty() || rollNumberStr.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields.");
            return;
        }

        try {
            int rollNumber = Integer.parseInt(rollNumberStr);
            Student student = new Student(name, rollNumber, grade);
            sms.addStudent(student);
            nameTextField.setText("");
            rollNumberTextField.setText("");
            gradeTextField.setText("");
            outputTextArea.append("Student added successfully!\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid roll number format. Please enter a valid number.");
        }
    }

    private void searchStudent() {
        String rollNumberStr = rollNumberTextField.getText();

        if (rollNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the roll number to search.");
            return;
        }

        try {
            int rollNumber = Integer.parseInt(rollNumberStr);
            Student student = sms.searchStudent(rollNumber);
            if (student != null) {
                outputTextArea.setText("Student Found:\n" +
                        "Name: " + student.getName() + "\n" +
                        "Roll Number: " + student.getRollNumber() + "\n" +
                        "Grade: " + student.getGrade() + "\n");
            } else {
                outputTextArea.setText("Student not found!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid roll number format. Please enter a valid number.");
        }
    }

    private void displayAllStudents() {
        java.util.List<Student> students = sms.getAllStudents();
        if (students.isEmpty()) {
            outputTextArea.setText("No students found.");
            return;
        }

        outputTextArea.setText("All Students:\n");
        for (Student student : students) {
            outputTextArea.append("Name: " + student.getName() + "\n" +
                    "Roll Number: " + student.getRollNumber() + "\n" +
                    "Grade: " + student.getGrade() + "\n" +
                    "--------------------------\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementSystemUI();
            }
        });
    }
}
