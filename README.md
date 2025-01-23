# SpringBooot_PCB-Vision

#spring_boot #back_end #tests
## **Dependencies:**

| Library Name         | Category        | Description                                                                                                                                 |
| -------------------- | --------------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| Spring Web           | WEB             | Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.                          |
| Lombok               | DEVELOPER TOOLS | Java annotation library which helps to reduce boilerplate code.                                                                             |
| Spring Data MongoDB  | NOSQL           | Store data in flexible, JSON-like documents, meaning fields can vary from document to document and data structure can be changed over time. |
| Spring Boot DevTools | DEVELOPER TOOLS | Provides fast application restarts, LiveReload, and configurations for enhanced development experience.                                     |

## **Test cases:**

#### **1. Create a New User**

- **Method**: ==POST==
- **URL**: `http://localhost:8080/api/users/register`
- **Body (JSON)**:
    
    ```json
    {
      "fname": "John",
      "lname": "Doe",
      "email": "john.doe@example.com",
      "phone": "1234567890",
      "password": "password123",
      "imageURL": "",
      "pcbs": []
    }
    ```

---
#### **2. Retrieve All Users**

- **Method**: ==GET==
- **URL**: `http://localhost:8080/api/users`

---
#### **3. Retrieve User by ID**

- **Method**: ==GET==
- **URL**: `http://localhost:8080/api/users/{id}`
- Replace `{id}` with the `id` of an existing user.
---
#### **4. Update User Data**

- **Method**: ==PUT==
- **URL**: `http://localhost:8080/api/users/{id}`
- Replace `{id}` with the `id` of the user.
- **Body (JSON)**:
    
    ```json
    {
      "fname": "John Updated",
      "lname": "Doe Updated",
      "email": "john.updated@example.com",
      "phone": "9876543210",
      "password": "newpassword123",
      "imageURL": "new-image-url",
      "pcbs": []
    }
    ```
---
#### **5. Login**

- **Method**: ==POST==
- **URL**: `http://localhost:8080/api/users/login`
- **Body (JSON)**:
    
    ```json
    {
      "email": "john.updated@example.com",
      "password": "newpassword123"
    }
    ```
---

#### **6. Retrieve All PCBs for a Specific User**

- **Method**: ==GET==
- **URL**: `http://localhost:8080/api/users/{id}/pcbs`
- Replace `{id}` with the `id` of the user.
---

#### **7. Add PCB**
- **Method:** ==POST==
- **URL:** `/api/users/{existingUserId}/pcbs`

```Json
{
  "name": "Test PCB",
  "image1": "assets/test_pcb.png",
  "image2": "assets/test_components.png",
  "defects": [
    {
      "defectName": "Solder Bridge",
      "description": "Short between pins 1 and 2."
    }
  ],
  "components": [
    {
      "type": "Resistors",
      "details": [
        "4 - 10KOHM"
      ]
    }
  ]
}
```

```Json
{
  "name": "Test PCB",
  "image1": "assets/test_pcb.png",
  "image2": "assets/test_components.png",
  "defects": [],
  "components": []
}
```

---
#### **8. Edit PCB for specific user**

* Method: ==PUT==
* URL: `http://localhost:8080/{USER_ID}/pcbs/{PCB_ID}`
* Body(JSON):

```JSON
{
  "name": "Updated PCB Name",
  "image1": "new_image1.png",
  "image2": "new_image2.png",
  "defects": [
    {
      "defectName": "bad solder updated",
      "description": "Updated defect description."
    }
  ],
  "components": [
    {
      "type": "Resistors",
      "details": [
        "5 - 1KOHM",
        "4 - 2KOHM"
      ]
    },
    {
      "type": "Capacitors",
      "details": [
        "4 Blue Electrolytic Capacitors"
      ]
    }
  ]
}
```

---
---
#### **9. Delete PCB**

- **Method:** ==DELETE==
    
- **URL:** `/api/users/{existingUserId}/pcbs/{existingPcbId}`

---

#### **10. Delete User**

- **Method**: ==DELETE==
- **URL**: `http://localhost:8080/api/users/{id}`
- Replace `{id}` with the `id` of the user.
---

### Group: 
<div align=center>

| **Assigned**          | **Name**                  |
| --------------------- | ------------------------- |
| Controller            | ضحى رجب  & عبدالرحمن أحمد |
| Service               | استر عماد & فاطمة محمد    |
| Repository and models | عبدالرحمن محمد حسن        |
</div>
