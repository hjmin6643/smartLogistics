/**
 *  B2B 사이트 사용 스크립트
 */
function toggleAnswer(o) {
    if (!$(o).parent().parent('tr').hasClass('on'))
        $('.tb_toggle tr').removeClass('on');
    $(o).parent().parent('tr').toggleClass('on').next('.a').toggleClass('on');
}

function go_temp(solutionId) {
    var url = "http://b2b.tworld.co.kr/bizts/solution/solutionTemplate.bs";
    var paramObj = {
        1: { name: "solutionId", value: solutionId },
        2: { name: "solId", value: solutionId },
        3: { name: "viewType", value: "P" }
    };
    submitPost(url, paramObj);
}

function submitPost(url, paramObj) {
    if (typeof(paramObj) === "undefined" ||
        typeof(paramObj) !== "object") {
        return;
    }
    // 이미 생성된 폼이 있으면 삭제
    if ($("#_postForm").size() > 0) {
        $("#_postForm").remove();
    }
    // 폼 생성 및 팝업 오픈
    var $form = $("<form />", { name: "_postForm", id: "_postForm", method: "POST" });
    $form.appendTo("body");
    $.each(paramObj, function(index, param) {
        $form.append($("<input />", { type: "hidden", name: param.name, value: param.value }));
    });
    $form.attr("action", url);
    $form.submit();
}

function goQuestion() {
    location.href = "http://b2b.tworld.co.kr/bizts/contact/contact.bs";
}

function subTab(o) {
    obj = $(document.getElementById(o));
    $(window).scrollTop(obj.position().top - 180);
}

function showReview(n) {
    $('.tb_toggle tr').removeClass('on');
    var $tab = $('.tab_review li')
    var $review = $('.board_review');
    $tab.removeClass('on');
    $tab.eq(n - 1).addClass('on');
    $review.hide();
    $review.eq(n - 1).show();
}

function sltCategory(o, str) {
    $('.lst_category li a').removeClass('on');
    $(o).addClass('on');
    var $faq = $('.tb_faq tbody tr');
    $faq.filter('.a').removeClass('on');
    if (str) {
        $faq.hide();
        $faq.filter('.q').children("td:not('.tit'):contains(" + str + ")").parent('tr').show();
    } else {
        $faq.filter('.q').show();
    }

}