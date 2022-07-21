let reg1 = /(^[A-Za-z()]{1,12})(\s{1}[A-Za-z()]{1,12})?(\s{1}[A-Za-z()]{1,12})?$/;

let inp1 = document.querySelector('#name');

let span1 = document.querySelector('.span1');


document.querySelector('.btn-success').onclick = function (e) {
    if (!validate(reg1, inp1.value)) {
        e.preventDefault();
        notValid(inp1, span1, 'Incorrect data!');
    }
};

function validate(regex, inp) {
    return regex.test(inp);
}

function notValid(inp, el, mess) {
    inp.classList.add('is-invalid');
    el.innerHTML = mess;
}











