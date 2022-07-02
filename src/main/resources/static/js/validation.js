let reg1 = /^[a-zA-Z]+$/;
let reg2 = /^\d{1,2}$/;
let reg3 = /^\d{1,8}$/;

let inp1 = document.querySelector('#type');
let inp2 = document.querySelector('#age');
let inp3 = document.querySelector('#price');

let span1 = document.querySelector('.span1');
let span2 = document.querySelector('.span2');
let span3 = document.querySelector('.span3');

document.querySelector('.btn').onclick = function (e) {
    if (!validate(reg1, inp1.value)) {
        e.preventDefault();
        notValid(inp1, span1, 'Некорректное значение в поле "тип" !');
    }
    if (!validate(reg2, inp2.value)) {
        e.preventDefault();
        notValid(inp2, span2, 'Некорректное значение в поле "возраст" !');
    }
    if (!validate(reg3, inp3.value)) {
        e.preventDefault();
        notValid(inp3, span3, 'Некорректное значение в поле "цена"!');
    }

};

function validate(regex, inp) {
    return regex.test(inp);
}

function notValid(inp, el, mess) {
    inp.classList.add('is-invalid');
    el.innerHTML = mess;
}

