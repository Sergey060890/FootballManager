let reg1 = /(^[A-Za-z]{1,12})(\s{1}[A-Za-z]{1,12})?(\s{1}[A-Za-z]{1,12})?$/;
let reg2 = /(^[A-Za-z]{1,12})(\s{1}[A-Za-z]{1,12})?(\s{1}[A-Za-z]{1,12})?$/;
let reg3 = /(^[A-Za-z]{1,12})(\s{1}[A-Za-z]{1,12})?(\s{1}[A-Za-z]{1,12})?$/;
let reg4 = /(^[A-Za-z]{1,12})(\s{1}[A-Za-z]{1,12})?(\s{1}[A-Za-z]{1,12})?$/;
let reg5 = /(^[A-Za-z]{1,12})(\s{1}[A-Za-z]{1,12})?(\s{1}[A-Za-z]{1,12})?$/;

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

document.querySelector('.btn-success').onclick = function (e) {
    if (!validate(reg1, inp1.value)) {
        e.preventDefault();
        notValid(inp1, span1, 'Incorrect data in the "name" field!');
    }
    if (!validate(reg2, inp2.value)) {
        e.preventDefault();
        notValid(inp2, span2, 'Incorrect data in the "city" field!');
    }
    if (!validate(reg3, inp3.value)) {
        e.preventDefault();
        notValid(inp3, span3, 'Incorrect data in the "country" field!');
    }
    if (!validate(reg4, inp4.value)) {
        e.preventDefault();
        notValid(inp4, span4, 'Incorrect data in the "stadium" field!');
    }
    if (!validate(reg5, inp5.value)) {
        e.preventDefault();
        notValid(inp5, span5, 'Incorrect data in the "coach" field!');
    }
};

function validate(regex, inp) {
    return regex.test(inp);
}

function notValid(inp, el, mess) {
    inp.classList.add('is-invalid');
    el.innerHTML = mess;
}











