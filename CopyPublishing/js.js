
//     function dateCal(){
//     const today = new Date();           // 현재 날짜/시간
// const dayIndex = today.getDay();    // 요일 인덱스 (0: 일요일 ~ 6: 토요일)


window.onload=function() {
    const date = new Date();
    const Year = date.getFullYear();
    const Month = date.getMonth(); // 0 ~ 11
    const today = date.getDate();

    const firstDay = new Date(Year, Month, 1).getDay(); // 0 (일) ~ 6 (토)
    const daysInMonth = new Date(Year, Month + 1, 0).getDate();

    document.getElementsByClassName("calFunc")[0].innerHTML+=(Year+'년 '+(Month+1)+'월');

    const calendar = document.querySelectorAll('.calMonth tbody td'); //td선택

    const startDay = firstDay == 0 ? 6 : firstDay - 1;// 요일 보정 

    for (let i = 0; i < daysInMonth; i++) {  // 날짜 채우기
        const cell = calendar[startDay + i];
        cell.innerHTML += (i + 1);

        if (i + 1 == today) {
            cell.classList.add('today');
        }
    }
};

function lastMonth(){
    const date = new Date();
    const Year = date.getFullYear();
    const Month = date.getMonth()+1; 
    
    const firstDay = new Date(Year, Month, 1);
    const lastDay = new Date(Year, Month + 1, 0);

    document.querySelectorAll('.calMonth tbody td').forEach(function() {
        this.textContent = '';
        //this.classList.remove('today');
    });
    const daysInMonth = new Date(Year, Month + 1, 0).getDate();
     document.getElementsByClassName("calFunc")[0].innerHTML=(Year+'년 '+(Month+1)+'월');

    const calendar = document.querySelectorAll('.calMonth tbody td'); //td선택

    const startDay = firstDay === 0 ? 6 : firstDay - 1;// 요일 보정 

    for (let i = 0; i < daysInMonth; i++) {  // 날짜 채우기
        const cell = calendar[startDay + i];
        this.textContent += '✅'+i + 1;
    }
}

function nextMonth(){
    const date = new Date();
    const Year = date.getFullYear();
    const Month = date.getMonth()-1; 
    
    const firstDay = new Date(Year, Month, 1);
    const lastDay = new Date(Year, Month + 1, 0);

    document.querySelectorAll('.calMonth tbody td').forEach(function() {
        cell.textContent = '';
        //this.classList.remove('today');
    });

     document.getElementsByClassName("calFunc")[0].innerHTML+=(Year+'년 '+(Month+1)+'월');

    const calendar = document.querySelectorAll('.calMonth tbody td'); //td선택

    const startDay = firstDay === 0 ? 6 : firstDay - 1;// 요일 보정 

    for (let i = 0; i < daysInMonth; i++) {  // 날짜 채우기
        const cell = calendar[startDay + i];
        cell.textContent += i + 1;
    }
}