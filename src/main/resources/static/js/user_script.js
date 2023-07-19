
fetch('/getUser')
    .then(response => response.json())
    .then(user => {
        const table = document.getElementById('userTable');

        const row = table.insertRow();

        const idCell = row.insertCell();
        idCell.innerHTML = user.id;

        const nameCell = row.insertCell();
        nameCell.innerHTML = user.username;

        const surnameCell = row.insertCell();
        surnameCell.innerHTML = user.surname;

        const ageCell = row.insertCell();
        ageCell.innerHTML = user.age;

        const emailCell = row.insertCell();
        emailCell.innerHTML = user.email;

        const roleCell = row.insertCell();
        const roles = user.authorities.map(obj => obj.authority);
        roleCell.innerHTML = roles.join(', ');

        const headerInfo = document.getElementById('headerInfo');
        headerInfo.textContent = `${user.email} with roles: ${roleCell.innerHTML}`;
    })
    .catch(error => {
        console.error('Ошибка получения данных:', error);
    });
