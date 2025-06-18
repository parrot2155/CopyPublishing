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

function lastMonth() {                   //다음달 넘기기기
    currentDate.setMonth(currentDate.getMonth() - 1);
    renderCalendar(currentDate);
}
 
function nextMonth() {                   //이전달달 넘기기기
    currentDate.setMonth(currentDate.getMonth() + 1);
    renderCalendar(currentDate);
}

document.addEventListener('DOMContentLoaded', () => {           
    let menuIcon = document.getElementById('menuIcon');
    let dropdownMenu = document.getElementById('dropdownMenu'); ///////////드롭다운 메뉴뉴

      
    menuIcon.addEventListener('click', () => {                   
        if (dropdownMenu.style.display === 'none') {
            dropdownMenu.style.display = 'block';
        } else {
            dropdownMenu.style.display = 'none';
        }
    });

    //닫기
    document.addEventListener('click', function (event) {
        if (!menuIcon.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.style.display = 'none';
        }
    });
});

document.addEventListener('DOMContentLoaded', () => {
    let mailIcon = document.getElementById('mailIcon');
    let dropdownMenu = document.getElementById('dropdownmail');

      
    mailIcon.addEventListener('click', () => {
        if (dropdownmail.style.display === 'none') {
            dropdownmail.style.display = 'block';
        } else {
            dropdownmail.style.display = 'none';
        }
    });

    //바깥에 눌러도 닫기
    document.addEventListener('click', function (event) {
        if (!mailIcon.contains(event.target) && !dropdownmail.contains(event.target)) {
            dropdownmail.style.display = 'none';
        }
    });
});






// -------------------카테고리

document.addEventListener('DOMContentLoaded', () => {
  const buttons = document.querySelectorAll('.category input[type="button"]');
  
  buttons.forEach(button => {
    button.addEventListener('click', function() {
      const parentDiv = this.parentElement;
      const existingInputArea = parentDiv.nextElementSibling;

      // 이미 input-area가 있으면 제거 (토글 동작)
      if (existingInputArea?.classList?.contains('input-area')) {
        existingInputArea.remove();
        return;
      }

      // 없으면 생성
      const inputdiv = document.createElement('div');
      inputdiv.className = 'input-area';

      const input = document.createElement('input');
      input.type = 'text';
      input.placeholder = '할 일 입력';

      const addBtn = document.createElement('button');
      addBtn.textContent = '추가';

      addBtn.addEventListener('click', () => {
        const text = input.value.trim();
        if (!text) return;

        const todoItem = document.createElement('div');

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';

        const span = document.createElement('span');
        span.textContent = text;

        const deleteBtn = document.createElement('button');
        deleteBtn.textContent = '❌';
        deleteBtn.style.marginLeft = '10px';
        deleteBtn.addEventListener('click', () => {
          todoItem.remove();
        });

        todoItem.appendChild(checkbox);
        todoItem.appendChild(span);
        todoItem.appendChild(deleteBtn);

        inputdiv.parentNode.insertBefore(todoItem, inputdiv.nextSibling);
        input.value = '';
      });

      inputdiv.appendChild(input);
      inputdiv.appendChild(addBtn);
      parentDiv.parentNode.insertBefore(inputdiv, parentDiv.nextSibling);
    });
  });
});
document.getElementById("registerCategory").addEventListener("click", function () {
    // + 버튼 클릭 시 동작하는 함수 직접 호출 또는 버튼 클릭 이벤트 발생
    document.getElementById("cate1").click();
});


// 카테고리 토글
//디자인 새로 넣음
//footer 고정되게 제작
//드롭박스 날라가는거 해결못함. 그러나 데스크탑에서는 문제없이 보이긴 함.