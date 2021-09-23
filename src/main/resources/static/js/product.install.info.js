var cameraMonthPrice = new Array(28500, 34733, 41944, 48178, 61744, 67978, 74211, 80444, 94989, 101222, 107456, 113689, 119922, 126156, 132389);

function showPrice() {
    var insideCnt = Number($("#insideCameraCnt").val());
    var outsideCnt = Number($("#outsideCameraCnt").val());
    var isDuplex = Number($("#isDuplex").val());
    var roomSize = Number($("#roomSize").val());
    var totalCnt = insideCnt + outsideCnt;

    if (totalCnt > 15) {
        alert('최대 카메라 대수는 실내/외 총합 15대 입니다.');
        return;
    } else if (totalCnt == 0) {
        alert('최소 1대 이상의 카메라를 선택해 주세요.');
        return;
    } else if (isDuplex == -1) {
        alert('단층/복층 여부를 선택해주세요.')
        return;
    } else if (roomSize == -1) {
        alert('평수를 선택해 주세요.');
        return;
    }
    var totalPrice = (cameraMonthPrice[totalCnt - 1] + (outsideCnt * 1000)) + (((roomSize - 20) / 10) * 500) + (isDuplex * totalCnt * 500);
    $("#totalEstimate").text(getCommaPrice(totalPrice));

}

function getCommaPrice(num) {

    var len, point, str;
    num = num + "";
    point = num.length % 3;
    len = num.length;

    str = num.substring(0, point);
    while (point < len) {
        if (str != "") str += ",";
        str += num.substring(point, point + 3);
        point += 3;
    }

    return str;
}

function checkMaxCount(gubun) {
    var insideCnt = Number($("#insideCameraCnt").val());
    var outsideCnt = Number($("#outsideCameraCnt").val());
    var totalCnt = insideCnt + outsideCnt;
    if (totalCnt > 15) {
        if (gubun == 0) {
            $("#insideCameraCnt").prop("selectedIndex", 0);
            $("#insideCameraCnt").val("0").prop("selected", true).prev('.select').getInstance().resetSB();
        } else if (gubun == 1) {
            $("#outsideCameraCnt").prop("selectedIndex", 0);
            $("#outsideCameraCnt").val("0").prop("selected", true).prev('.select').getInstance().resetSB();
        }
        alert('최대 카메라 대수는 실내/외 총합 15대 입니다.');
    }
}