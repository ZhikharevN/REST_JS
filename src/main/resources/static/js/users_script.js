feelUsersTable();
feelUserInfo();

async function feelUserInfo() {
    fetch('/getUser')
        .then(response => response.json())
        .then(user => {
            const roles = user.authorities.map(obj => obj.authority);
            const headerInfo = document.getElementById('headerInfo');
            headerInfo.textContent = `${user.email} with roles: ${roles.join(', ')}`;
        })
        .catch(error => {
            console.error('Ошибка получения данных:', error);
        });
}

async function feelUsersTable() {
    fetch('/admin/getUsers')
        .then(response => response.json())
        .then(users => {
            let table = $('#usersTable tbody');
            table.empty();
            users.forEach(user => {
                const roles = user.authorities.map(obj => obj.authority);
                let tableFilling = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.surname}</td>
                            <td>${user.age}</td>    
                            <td>${user.email}</td> 
                            <td>${roles.join(', ')}</td>
                            <td>
                                <button type="button" data-id="${user.id}" data-action="edit" class="btn btn-info" 
                                data-bs-toggle="modal" data-bs-target="#editModal" id="editButton">Edit</button>
                            </td>
                            <td>
                                <button type="button" data-id="${user.id}" data-action="delete" class="btn btn-danger" 
                                data-bs-toggle="modal" data-bs-target="#deleteModal" id="deleteButton">Delete</button>
                            </td>
                        </tr>
                )`;
                table.append(tableFilling);
            })
        })
        .catch(error => {
            console.error('Ошибка получения данных:', error);
        })
}


// ----------------------------- Fill modal window --------------------------------------------
document.getElementById('usersTable').addEventListener("click", (e) => {
    e.preventDefault();
    let deleteButtonPressed = e.target.id == "deleteButton";
    let editButtonPressed = e.target.id == "editButton";

    if (deleteButtonPressed) {
        let idUser = e.target.getAttribute('data-id');
        fetch(`/admin/getUser/${idUser}`)
            .then(response => response.json())
            .then(user => {
                document.getElementById("idDelete").value = user.id;
                document.getElementById("nameDelete").value = user.username;
                document.getElementById("surnameDelete").value = user.surname;
                document.getElementById("ageDelete").value = user.age;
                document.getElementById("emailDelete").value = user.email;
                let rolesArr = user.authorities.map(obj => obj.authority)
                let roleChoice = $('#roleDelete');
                roleChoice.empty();
                for (i = 0; i < rolesArr.length; i++) {
                    roleChoice.append(`<option>${rolesArr[i]}</option>`);
                }
            })
    }

    if (editButtonPressed) {
        let idUser = e.target.getAttribute('data-id');
        fetch(`/admin/getUser/${idUser}`)
            .then(response => response.json())
            .then(user => {
                document.getElementById("idEdit").value = user.id;
                document.getElementById("nameEdit").value = user.username;
                document.getElementById("surnameEdit").value = user.surname;
                document.getElementById("ageEdit").value = user.age;
                document.getElementById("emailEdit").value = user.email;
                document.getElementById("passwordEdit").value = user.password;
            })

        fetch('/admin/getRoles')
            .then(response => response.json())
            .then(roles => {
                let roleChoiceEdit = $('#roleEdit');
                roleChoiceEdit.empty();
                roles.forEach(role => {
                    roleChoiceEdit.append(`<option value="${role.id}">${role.name}</option>`);
                })
            })
    }
})


// --------------------------  Delete submit ----------------------------

const deleteForm = document.getElementById('formModelDelete')
deleteForm.addEventListener('submit', handleDeleteFormSubmit)

async function handleDeleteFormSubmit(event) {
    event.preventDefault()
    deleteUser(deleteForm.elements.namedItem('id').value);
}

async function deleteUser(id) {
    await fetch(`/admin/delete/${id}`, {
        method: 'DELETE',
    })
        .then(document.getElementById('buttonDismiss').click())

    feelUsersTable();
}

// --------------------------- Edit submit ------------------------------

const editForm = document.getElementById('formModelEdit')
editForm.addEventListener('submit', handleEditFormSubmit)

async function handleEditFormSubmit(event) {
    event.preventDefault();
    editUser();
}

async function editUser() {
    const data = new FormData(editForm)
    await fetch('/admin/editUser', {
        method: 'PATCH',
        body: data,
    })
        .then(document.getElementById('buttonDismissEdit').click())

    feelUsersTable();
}















