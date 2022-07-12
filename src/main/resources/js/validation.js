let reg1 = /(^[A-Za-zА-Яа-яЁё]{1,12})(\s{1}[A-Za-zА-Яа-яЁё]{1,12})?(\s{1}[A-Za-zА-Яа-яЁё]{1,12})?$/;
let reg2 = /^[1-9]{1}$|^[1-2]{1}[0-9]{1}$|^30$/;
let reg3 = /^[1-9]\d{0,3}$/;

let inp1 = document.querySelector('#name');
let inp2 = document.querySelector('#city');
let inp3 = document.querySelector('#country');
let inp4 = document.querySelector('#stadium');
let inp5 = document.querySelector('#coach');

let span1 = document.querySelector('.span1');
let span2 = document.querySelector('.span2');
let span3 = document.querySelector('.span3');
let span4 = document.querySelector('.span4');
let span5 = document.querySelector('.span5');

document.querySelector('.btn').onclick = function (e) {
    if (!validate(reg1, inp1.value)) {
        e.preventDefault();
        notValid(inp1, span1, 'Введите имя из 1-3 слов');
    }
    if (!validate(reg1, inp2.value)) {
        e.preventDefault();
        notValid(inp2, span2, 'Введите тип из 1-3 слов');
    }
    if (!validate(reg2, inp3.value)) {
        e.preventDefault();
        notValid(inp3, span3, 'Введите возраст 1-30 лет');
    }
    if (!validate(reg3, inp4.value)) {
        e.preventDefault();
        notValid(inp4, span4, 'Введите цену 1-9999$');
    }
};

function validate(regex, inp) {
    return regex.test(inp);
}

function notValid(inp, el, mess) {
    inp.classList.add('is-invalid');
    el.innerHTML = mess;
}