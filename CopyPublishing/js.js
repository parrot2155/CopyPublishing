let currentDate = new Date();
window.onload = function () {
    renderCalendar(currentDate);
};

function renderCalendar(date) {
    const Year = date.getFullYear();
    const Month = date.getMonth(); // 0 ~ 11
    const today = new Date();

    const firstDay = new Date(Year, Month, 1).getDay(); // 0 ~ 6 
    const daysInMonth = new Date(Year, Month + 1, 0).getDate();

    document.getElementsByClassName("calFunc")[0].innerHTML = (Year + '년 ' + (Month + 1) + '월');

    const calendar = document.querySelectorAll('.calMonth tbody td');
    calendar.forEach(cell => {
        cell.innerHTML = '';
        cell.classList.remove('today');
        cell.classList.remove('selected');
        cell.style.color = ''; 
    });

    const startDay = firstDay === 0 ? 6 : firstDay - 1;

    for (let i = 0; i < daysInMonth; i++) {
        const cell = calendar[startDay + i];
        const dayNum = i + 1;
        cell.innerHTML = dayNum;



        if (
            Year === today.getFullYear() &&
            Month === today.getMonth() &&
            dayNum === today.getDate()
        ) {
            cell.classList.add('today');
        }




        cell.onclick = function () {
            
            document.querySelectorAll('.calMonth tbody td').forEach(c => {
                c.classList.remove('selected');
                c.style.color = '';
            });

           
            this.classList.add('selected');
            this.style.color = 'blue';
        };
    }
}

function lastMonth() {
    currentDate.setMonth(currentDate.getMonth() - 1);
    renderCalendar(currentDate);
}

function nextMonth() {
    currentDate.setMonth(currentDate.getMonth() + 1);
    renderCalendar(currentDate);
}


// -------------------카테고리리

document.addEventListener('DOMContentLoaded', () => {
  const buttons = document.querySelectorAll('.category input[type="button"]');
  buttons.forEach(button => {


    button.addEventListener('click', function() {
      const parentDiv = this.parentElement;
      const inputdiv = document.createElement('div');
      

      const input = document.createElement('input');
      input.type = 'text';
      input.placeholder = '할 일 입력';

      const addBtn = document.createElement('button');
      addBtn.textContent = '추가';
      addBtn.addEventListener('click', () => {
        const text = input.value.trim();

        const todoItem = document.createElement('div');
        todoItem.textContent = "✅" + text;

        inputdiv.parentNode.insertBefore(todoItem, inputdiv.nextSibling);
        input.value = '';
      });

      inputdiv.appendChild(input);
      inputdiv.appendChild(addBtn);

      parentDiv.parentNode.insertBefore(inputdiv, parentDiv.nextSibling);
    });
  });
});
//입력받은 창은 없애는 기능 못넣음
//할일 카테고리에 체크박스 기능 못넣음
//삭제하는 기능 못넣음
//추ㅏ되는 박스에 css를 따로 못넣었음/.....

