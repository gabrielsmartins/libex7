package br.edu.utfpr.adapters.gui.handlers.users.students;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.utfpr.adapters.gui.views.users.students.SaveStudentView;
import br.edu.utfpr.libex7.application.domain.users.Student;
import br.edu.utfpr.libex7.application.ports.in.users.SaveUserUseCase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public final class ButtonSaveStudentHandler implements EventHandler<ActionEvent> {
	private final SaveStudentView view;
	private final SaveUserUseCase useCase;

	public ButtonSaveStudentHandler(SaveStudentView view, SaveUserUseCase useCase) {
		this.view = view;
		this.useCase = useCase;
	}

	@Override
	public void handle(ActionEvent event) {
		
		try {
			TextField txtName = view.getTxtName();
			TextField txtDob = view.getTxtDob();
			TextField txtStudentNumber = view.getTxtStudentNumber();
			String name = txtName.getText().toUpperCase().trim();
			LocalDate dob = LocalDate.parse(txtDob.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			Long studentNumber = Long.parseLong(txtStudentNumber.getText());
			
			Student user = new Student();
			user.setName(name);
			user.setDob(dob);
			user.setStudentNumber(studentNumber);
			
			useCase.save(user);
			Alert alert = new Alert(AlertType.CONFIRMATION, "Estudante cadastrado com sucesso");
			alert.showAndWait();
		}catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR, "Erro ao cadastrar estudante");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
		
	}
}