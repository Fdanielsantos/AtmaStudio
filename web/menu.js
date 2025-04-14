/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let menuItem = document.querySelectorAll('.item-menu')

function selectLink() {
    menuItem.forEach((item) =>
        item.classList.remove('ativo')
    )
    this.classList.add('ativo')

}
menuItem.forEach((item) =>
    item.addEventListener('click', selectLink))


//Expandir o  menu
let btnExp = document.querySelector('#btn-exp')
let menuSide = document.querySelector('.menu-lateral')

 
btnExp.addEventListener('click', function () {
    menuSide.classList.toggle('expandir')
})
