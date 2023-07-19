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

    fetch('/admin/getRoles')
        .then(response => response.json())
        .then(roles => {
            let roleChoiceEdit = $('#roleSelect');
            roleChoiceEdit.empty();
            roles.forEach(role => {
                roleChoiceEdit.append(`<option value="${role.id}">${role.name}</option>`);
            })
        })
}



const createForm = document.getElementById('newUserForm')
createForm.addEventListener('submit', handleDeleteFormSubmit)
async function handleDeleteFormSubmit(event) {
    event.preventDefault()
    createUser();
}


async function createUser(){
    const data = new FormData(createForm)
    await fetch('/admin/create', {
        method: 'POST',
        body: data,
    })
}





