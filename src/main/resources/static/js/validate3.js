let reg1 = /(^[A-Za-z]{1,12})(\s{1}[A-Za-z]{1,12})?(\s{1}[A-Za-z]{1,12})?$/;
let reg2 = /(^[A-Za-z]{1,12})(\s{1}[A-Za-z]{1,12})?(\s{1}[A-Za-z]{1,12})?$/;
let reg3 = /(^[A-Za-z]{1,12})(\s{1}[A-Za-z]{1,12})?(\s{1}[A-Za-z]{1,12})?$/;
let reg4 = /^[1-9]{1}$|^[1-5]{1}[0-9]{1}$|^60$/;

let inp1 = document.querySelector('#name');
let inp2 = document.querySelector('#surname');
let inp3 = document.querySelector('#country');
let inp4 = document.querySelector('#age');


let span1 = document.querySelector('.span6');
let span2 = document.querySelector('.span7');
let span3 = document.querySelector('.span8');
let span4 = document.querySelector('.span9');


document.querySelector('.btn-success').onclick = function (e) {
    if (!validate(reg1, inp1.value)) {
        e.preventDefault();
        notValid(inp1, span1, 'Неверные данные в поле "имя"! /Incorrect data in the "name" field!');
    }
    if (!validate(reg2, inp2.value)) {
        e.preventDefault();
        notValid(inp2, span2, 'Неверные данные в поле "фамилия"! /Incorrect data in the "surname" field!');
    }
    if (!validate(reg3, inp3.value)) {
        e.preventDefault();
        notValid(inp3, span3, 'Неверные данные в поле "страна"! /Incorrect data in the "country" field!');
    }
    if (!validate(reg4, inp4.value)) {
        e.preventDefault();
        notValid(inp4, span4, 'Неверные данные в поле "возраст"! /Incorrect data in the "age" field!');
    }

};

function validate(regex, inp) {
    return regex.test(inp);
}

function notValid(inp, el, mess) {
    inp.classList.add('is-invalid');
    el.innerHTML = mess;
}











