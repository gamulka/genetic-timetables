# genetic-timetables
Timetable generation using a genetic algorithm with a simple set of rules.

This program was used to solve the University Timetabling combinatorial problem, and it produces a schedule for a small number of courses, which are received through XML in a specific format. The output might be used as is or improved with human assistance.

The input XML is structured as follows:

```xml
<?xml version="1.0"?>
<Horario>
	<Profesores>
		<Profesor>
			<Nombre>John Doe</Nombre>
			<Imparte>
				<Curso>
					<Cod>The code of the course</Cod>
					<Grupos>Number of groups i.e. 1</Grupos>
				</Curso>
			</Imparte>
		</Profesor>
		... add more professors
	</Profesores>	
	
	<Espacios>
		<Aula>Classroom 1</Aula>
		<Aula>Classroom 2</Aula>
		... add more "places"
		<Lab>Laboratory 1</Lab>
	</Espacios>

	<Cursos>
		<Curso>
			<Cod>The code of the course</Cod>
			<Nombre>Name of the course</Nombre>
			<Horas>Total hours</Horas>
			<OcupaLab> 1 if lab is needed, else 0 </OcupaLab>
			<Tipo>Day/Night</Tipo>
		</Curso>
		... add more courses
	</Cursos>
</Horario>
```

An useful approach to this problem is nicely explained here: http://www.cs.le.ac.uk/~syang/Papers/MISTA09.pdf
