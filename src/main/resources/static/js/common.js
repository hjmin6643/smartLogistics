$(function() {
    var token = $("meta[name=_csrf]").attr("content");
    var header = $("meta[name=_csrf_header]").attr("content");
    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
    });

    $.extend($.fn.dataTable.defaults, {
        deferRender: true,
        dom: "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
            "<'row mb-2'<'col-sm-12'rt>>" +
            "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
        language: {
            url: "/js/dataTables/datatables.korean.json"
        },
        columnDefs: [{
            defaultContent: "",
            targets: "_all",
        }],
        initComplete: function (settings, json) {
            // var $loading = $("#loading");
            // if ($loading.is(":visible")) {
            //     $loading.hide();
            // }
        },
    });
});

function toast(type, msg) {
    toastr.clear();
    toastr.options.positionClass = "toast-top-center";
    toastr[type](msg);
}

function dialog(title, msg, options) {
    options = options || {};
    var createDialog = "<div class=\"modal fade\" id=\"commonModal\" tabindex=\"-1\" role=\"dialog\" aria-hidden=\"true\" style=\"display:none;\">"
        + "<div class=\"modal-dialog\" role=\"document\"><div class=\"modal-content\"><div class=\"modal-header\"><h4 class=\"modal-title\">Modal Title</h4>"
        + "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>"
        + "<div class=\"modal-body\"><p>Modal Body</p></div><div class=\"modal-footer justify-content-between\">"
        + "<button type=\"button\" class=\"btn ml-auto\" data-dismiss=\"modal\">Close</button></div></div></div></div>";
    var $modal = $(createDialog);
    $modal.data({
        title: title,
        msg: msg,
        options: options,
    });
    $modal.find(".modal-title").html(title);
    $modal.find(".modal-body").html(msg);
    $modal.find(".modal-dialog").addClass(options["dialogClass"] || "");
    if (options.hasOwnProperty("dialogWidth")) {
        $modal.find(".modal-dialog").css("maxWidth", options["dialogWidth"]);
    }
    $modal.find(".modal-content").addClass(options["contentClass"] || "");
    $modal.find(".modal-header").addClass(options["headerClass"] || "");
    $modal.find(".modal-body").addClass(options["bodyClass"] || "");
    $modal.find(".modal-footer").find("button").addClass(options["buttonClass"] || "btn-primary");
    if ($("#commonModal").length > 0) {
        $("#commonModal").modal("dispose");
        $("#commonModal").remove();
    }
    $("#content-wrapper").append($modal);
    var initialize = options["initialize"] || null;
    $modal.on("show.bs.modal", function (e) {
        if (typeof (initialize) === "function") {
            initialize($modal);
        }
    });
    var callback = options["callback"] || null;
    $modal.on("hidden.bs.modal", function (e) {
        if (typeof (callback) === "function") {
            callback($modal);
        }
        $modal.modal("dispose");
        $modal.remove();
        $(".modal-backdrop").remove();
    });
    $modal.modal({
        backdrop: options["backdrop"] || "static",
        keyboard: false
    });
    return $modal;
}

/**
 * 수직방향으로 같은 값을 가진 cell 을 merge 한다.
 * <입력 파라미터>
 * - table : Table 객체
 * - startRowIdx : 테이블의 몇 번째 row 에서부터 merge 를 수행할 지 결정하는 row's Index
 * - cellIdx : merge 하기 위한 테이블의 cell's Index
 */
function mergeVerticalCell(table, startRowIdx, cellIdx) {
    var rows = table.getElementsByTagName("tr");
    var numRows = rows.length;
    var numRowSpan = 1;
    var currentRow = null;
    var currentCell = null;
    var currentCellData = null;
    var nextRow = null;
    var nextCell = null;
    var nextCellData = null;
    for (var i = startRowIdx; i < (numRows - 1); i++) {   // i 는 row's index
        // 새롭게 cell merge 를 해야하면,
        // 현재(비교의 기준이 되는..) row, cell, data 구함
        if (numRowSpan <= 1) {
            currentRow = table.getElementsByTagName('tr')[i];
            currentCell = currentRow.getElementsByTagName('td')[cellIdx];
            currentCellData = currentCell.childNodes[0].data;
        }
        if (i < numRows - 1) {  // 현재 row 가 마지막 row 가 아니면
            // 다음 row, cell, data 구함
            if (table.getElementsByTagName('tr')[i + 1]) {
                nextRow = table.getElementsByTagName('tr')[i + 1];
                nextCell = nextRow.getElementsByTagName('td')[cellIdx];
                nextCellData = nextCell.childNodes[0].data;
                // 현재 cell == 다음 cell 이면, merge
                if (currentCellData == nextCellData) {
                    numRowSpan += 1;
                    currentCell.rowSpan = numRowSpan;
                    nextCell.style.display = 'none';
                // 현재 cell != 다음 cell 이면,
                // 새로운 현재(비교의 기준이 되는..) cell 을 구할 수 있도록 초기화
                } else {
                    numRowSpan = 1;
                }
            }
        }
    }
}

/**
 * 수직방향으로 같은 값을 가진 cell 을 merge 한다.
 * 단, mergeVerticalCell() 함수를 통해서 먼저 특정 cell 들이 merge 된 이후,
 * merge 된 cell 을 기준으로 merge 된 cell 의 범위 내에 포함되는 row 의 cell 에 대해서만 merge 한다.
 * <입력 파라미터>
 * - table : Table 객체
 * - startRowIdx : 테이블의 몇 번째 row 에서부터 merge 를 수행할 지 결정하는 row's Index
 * - basicCellIdx : 이미 merge 된 cell 중에서 기준이 되는 cell's index
 * - cellIdx : merge 하기 위한 테이블의 cell's Index
 */
function mergeDependentVerticalCell(table, startRowIdx, basicCellIdx, cellIdx) {
    var rows = table.getElementsByTagName("tr");
    var numRows = rows.length;
    var numRowSpan = 1;  // 초기화
    var countLoopInBasicMerge = 1;  // 초기화   merge 된 cell 내에서의 반복루프 처리 횟수
    var currentRow = null;
    var currentCell = null;
    var currentCellData = null;
    var nextRow = null;
    var nextCell = null;
    var nextCellData = null;
    var basicRowSpan = null;
    for (var i = startRowIdx; i < (numRows - 1); i++) {   // i 는 row's index
        // 기준 rowSpan 값 설정
        // basicCellIdx 에 해당하는 cell 의 rowSpan 값이 기준 rowSpan 범위가 됨.
        if (i == startRowIdx || (countLoopInBasicMerge == 1 && numRowSpan == 1)) {
            basicRowSpan = table.getElementsByTagName('tr')[i].getElementsByTagName("td")[basicCellIdx].rowSpan;
        }
        // 새롭게 cell merge 를 해야하면,
        // 현재(비교의 기준이 되는..) row, cell, data 구함
        if (numRowSpan <= 1) {
            currentRow = table.getElementsByTagName('tr')[i];
            currentCell = currentRow.getElementsByTagName('td')[cellIdx];
            currentCellData = currentCell.childNodes[0].data;
        }
        if (i < numRows - 1) {  // 현재 row 가 마지막 row 가 아니면
            if (countLoopInBasicMerge < basicRowSpan) {  // 기준 row 의 rowSpan 값을 초과해서 merge 할 수 없음.
                // 다음 row, cell, data 구함
                if (table.getElementsByTagName('tr')[i + 1]) {
                    nextRow = table.getElementsByTagName('tr')[i + 1];
                    nextCell = nextRow.getElementsByTagName('td')[cellIdx];
                    nextCellData = nextCell.childNodes[0].data;
                    // 현재 cell == 다음 cell 이면, merge
                    if (currentCellData == nextCellData) {
                        numRowSpan += 1;
                        currentCell.rowSpan = numRowSpan;
                        nextCell.style.display = 'none';
                    // 현재 cell != 다음 cell 이면,
                    // 새로운 현재(비교의 기준이 되는..) cell 을 구할 수 있도록 초기화
                    } else {
                        numRowSpan = 1;
                    }
                }
                countLoopInBasicMerge++;
            // 기준 rowSpan 범위 이상이면,
            // 새로운 rowSpan 을 설정할 수 있도록 값을 초기화
            } else {
                countLoopInBasicMerge = 1;
                numRowSpan = 1;
            }
        }
    }
}

function isEmpty(value) {
    if (value === "" || value === null || value === undefined
        || (typeof value === "object" && !Object.keys(value).length)) {
        return true;
    } else {
        return false;
    }
}

function num(num) {
    var temp = 0;
    try {
        temp = Number(num);
        if(isNaN(temp)) {
            temp = Number(num.replace(/[^0-9]/g, ""));
        }
    } catch (e) {
        temp = 0;
    }
    return temp
}

// Add Comma
function addComma(num) {
    var pattern = /(-?[0-9]+)([0-9]{3})/;
    var n = String(num);
    while(pattern.test(n)) {
        n = n.replace(pattern, "$1,$2");
    }
    return n;
}

// Remove Comma
function removeComma(num) {
    return num.replace(/[^0-9]/g, "").replace(/,/gi, "");
}

// Left Padding
function leftPad(str, padLen, padStr) {
    if (padStr.length > padLen) {
        return str;
    }
    str += "";
    padStr += "";
    while (str.length < padLen)
        str = padStr + str;
    str = str.length >= padLen ? str.substring(0, padLen) : str;
    return str;
}

// Right Padding
function rightPad(str, padLen, padStr) {
    if (padStr.length > padLen) {
        return str + "";
    }
    str += "";
    padStr += "";
    while (str.length < padLen)
        str += padStr;
    str = str.length >= padLen ? str.substring(0, padLen) : str;
    return str;
}

// Chrome Check
function isChrome() {
    var agent = (navigator.userAgent ? navigator.userAgent : "").toUpperCase();
    var result = false;
    if(agent.indexOf("CHROME") > -1) {
        result = true;
    } else if(agent.indexOf("SAFARI") > -1) {
        result = false;
    } else if(agent.indexOf("FIREFOX") > -1) {
        result = false;
    }
    return result;
}
