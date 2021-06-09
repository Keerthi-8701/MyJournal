# MyJournal
An online journal to track progress of projects

The project MyJournal is an online journal wherein student updates with the list of tasks and the deadlines of projects and assignments to be completed. 
It keeps track of the events that are to be completed and allows the user to modify edit and delete the entered records.
It stores all the past records of the events and displays all the events that have happened so far as a list so that the person need not take pain to write it in some journal manually. 
It also keeps track of the amount of time spent for a particular module or assignment in a single button click. 
It can also be used for performance analysis.

# Tools Required:
IDE - NetBeans 8.2  
Server - GlassFish Server 4.1.1  
Database - MySQL WorkBench 5.5.

# Languages Used:
Front-end – HTML,CSS.  
Back-end – Javascript,Servlets.

# Modules:
**LOGIN:**    
The project displays a login page initially. Upon successful login, the user will be directed to a page where all the pending events are listed. The main purpose of the login is to create a separate database for individual users. One table “login” stores all the users and passwords of the users and “n” number of tables for “n” users with table names as usernames are used for this project.    
  **SIGNUP:**  
On clicking signup link, the user would be directed to a page where he/she will be creating a new username and password. On clicking submit, the username and password would be updated to the database and a new separate table for the user would be created at the backend to store all the details of the particular user. Alphabets, numbers and underscores alone are allowed for username as table names can hold only these characters. Here the usernames are case insensitive while passwords are case sensitive (evident from the above screenshots).    
  **FORGOT PASSWORD:**  
On clicking this link, the user would be allowed to reset the password stored in the database. If the user forgets the username, they are redirected to an intimation page where the journal suggests him/her to create a new login instead.    
  **MYJOURNAL:**  
In this page, all the pending events that are fed by the user would be displayed against individual checkboxes which indicates the status of completion of the event. Upon completion, the user checks the checkbox and the date and time of event completion would be stored in the database. The status of the event is changed to completed.  
**ADD_NEW_EVENT:**  
Clicking the ‘+’ button in the MyJournal page directs the user to another page where it allows the user to add a new event, which collects the details from the user like event title, description of the event, Date and time before the event has to be completed.   
**MODIFY_EVENT:**  
Clicking on the modify button/link next to the events displayed in the MyJournal page will allow the user to edit the task entered.  
**START_TIMER:**  
Clicking on the start timer button/link next to the events displayed in the MyJournal page will allow the user to track the amount of time worked on the task entered. On clicking this button for the first time, the actual start time of the module/event would be recorded in the database.  
**ADD_COMPLETED_WORK:**  
Clicking on the checkbox next to the event listed in the MyJournal page would check for the amount of time worked, if its zero then it prompts the user to work on the event or log pervious work. Clicking on the “log previous work” will allow the user to enter actual start and end of the event along with the amount of time spent on the module.   
**COMPLETED_EVENTS:**  
Clicking on “Show Completed Modules” button in the MyJournal page will lead the user to a page where it lists all the completed tasks date-wise and time-wise along with the analysis of the work done, like comparing expected deadlines to actual deadlines, and giving percentage of excellence based on the commitment to deadline and the amount of time spent on the module. 



