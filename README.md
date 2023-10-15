[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)

# 3500 PA05 Project Repo: Digital Bullet Journal
Did you dreamed of a handy weekly planner that can be an useful organizer for your daily tasks and events?
Well then today is your lucky day!

## FLEXIBLE WEEK VIEW:
The week can begin on any day you want! Does your week start on Monday or Sunday? Well, you have the option to customize it all!
Start on Sunday?: 
![image](https://github.com/CS-3500-OOD/pa05-bujobujo/assets/122419958/5930db8e-f7ba-4d9d-8a94-953e15af974a)
You can even start Tuesday!:
<img width="1023" alt="image" src="https://github.com/CS-3500-OOD/pa05-bujobujo/assets/122419958/4ad452d9-3ff9-4a14-87ad-8a18bcdfdd06">

## CREATE TASKS & EVENTS:
You can add your tasks and events to each day of the week! There is even a task queue for better visualizing in the list of task you wish to complete.

![image](https://github.com/CS-3500-OOD/pa05-bujobujo/assets/122419958/10070cc1-0ab2-470c-8f47-3a699ad31b85)


## CUSTOMIZATION FOR MORE PRODUCTIVITY
You can set the maximum of events and tasks for each day so that you don't overload your self with too much work!

<img width="332" alt="image" src="https://github.com/CS-3500-OOD/pa05-bujobujo/assets/122419958/54b3fe57-94f9-4ece-8879-c0b58bb93869">

## SOLID PRINCIPLES:
**S** - Single-responsiblity Principle
- We applied the single responsibility principle to all the classes. For example, NewOrOpenEventHandler is only responsible for handling the New File/Open File window interaction, ToDoEvent represents the event and is only responsible for the operation on the event.

**O** - Open-closed Principle
- We applied this principle on the ToDoEvent and ToDoTask. We abstracted the ToDo class from these two classes so that ToDoEvent and ToDoTask is closed for the modification on the existing code and can be easily added more methods. Moreover, if a new ToDo datatype is introduced, it can easily extend the ToDo class.

**L** - Liskov Substitution Principle
- We applied this principle to the interface and abstract class. The ToDoEvent and ToDoTask datatype object can be replaced by the ToDo datatype object and won’t break the program. The writer datatype object can be replaced by the WriterImpl datatype object.

**I** - Interface Segregation Principle
- The WriterImpl, ReaderImpl and BulletJournalController class implement all the methods inside their implemented interface and use all of these methods.

**D** - Dependency Inversion Principle
- We followed the dependency inversion principle. For instance, DayToDos class does not depend on the ToDoEvent and ToDoTask class but instead relies on the higher level ToDo class.

## FUTURE EXTENSIONS
- More implementations regarding the setting can be added within the Set button. For example, you can add a setCategory feature so that you can **filter** the week of to-dos by category. **Mind changes** can also be implemented here. Through additional dialog promptss, the user can change their task/event name and duration/start time by entering the old task/event and updated task/event details.
  
- In the taskCreationDialog, an additional description and links option can allow the user to include a description of the todo and provide parse links into the description.
  
- Additional buttons like the Menu bar & Shortcuts can also be implemented with various action events handler.

- Moreover, with the weekly overview statistics, a **progress bar** of the completion status can also be implemented in the future.
  
- Currently, we support the user opening pre-existing files. This can be expanded into displaying pre-existing files across **multiple tabs**.
- With the use of index for each todo, the feature of **custom ordering** can also be implemented
  
- Since the duration and name is displayed in the list<DayToDos>, the sorting feature (**Sort By Name & Duration**) can also be implemented.
