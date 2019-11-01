# CloudMedi

Objective of the project -
To reduce the Delay and unavailability of medical history/data of patients before emergency operations leading to further life affecting complications.
Identification of the patient/victim at the emergency hour at the operation theater to get his medical data.
Delay in analyzing the conditions which has impact on the operation.

Working 
The app has two types of users: The patients and the doctors
Each user has a different interface to add and retrieve their respective data.Patients can add their medical history records along such as blood reports , X-rays , Scans , prescriptions etc 

At the time of emergencies the patient can directly identified via their biometric details and their risk profile, medical profile can be viewed.
This will ensure the proper surgery according to their previous medical condition
These can be used as replacement of physical records and as a unified medical records 
Doctors can directly access the RISK PROFILE which consists of the severe medical condition of patients which affects the surgeries. 
Doctors can view complete medical history of the patient with his consent 
Doctors can send the user’s records to another expert for their review with the patient consent 
At the time of emergencies the patient can directly identified via their biometric details and their risk profile, medical profile can be viewed.
This will ensure the proper surgery according to their previous medical condition

A part of the signup process is adding the patients fingerprint data. This help for quick retrieval of users medical records at the time of emergencies.
FIngerprint output is a 520 bytes BMP file which is enrolled and assigned a unique id ,stored in the firebase and can be accessed to check if the fingerprint is getting matched or not.


  Potential Impact 
 Accident victims requiring quick assistance
Can identify the unconscious patients and access their medical history
Patients are not required to carry their medical records everytime the consult a doctor.
Inter communication between doctors regarding a certain patient's condition which would decrease the time taken to access the condition.





Usage:
The finger print sensor is connected to the node Mcu, which is runs the code given. The enroll file is runned once to save the fingerprint of the user.
Once enrolled the name is stored in the firebase database. Then the fetch is used to fetch the fingerprints.
