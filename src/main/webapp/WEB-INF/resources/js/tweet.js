$("#div-tweet").click(function() {
  $("#addTweet").slideToggle("fast");
});

/*
 * show add-comment form
 */
var button = $(".tweet-add-comment")
button.click(function(event) {

  var formComment = $(event.currentTarget.parentElement.nextElementSibling);
  $(formComment).slideToggle("400");
});

/*
 * function for search input in navbar
 */
$(function(e) {
  $('.search-panel .dropdown-menu').find('a').click(function(e) {
    e.preventDefault();
    var param = $(this).attr("href").replace("#", "");
    var concept = $(this).text();
    $('.search-panel span#search_concept').text(concept);
    $('.input-group #search_param').val(param);
  });
});

$(function() {

  /*
   * show comments on click
   */
  var url = "http://localhost:8080/MyTwitter/";
  var button = $(".tweet-comments-button")
  button
    .on(
      "click",
      function showComments(event) {

        var urlAndId = "all/" +
          $(event.currentTarget).data("id");
        var htmlType = $(event.currentTarget).data("type");

        var commentListUl = $(event.currentTarget.parentElement)
          .siblings().find("ul.comments-list");
        console.log(commentListUl);
        var commentListDiv = $(event.currentTarget.parentElement.nextElementSibling.nextElementSibling);
        console.log(commentListDiv);
        ajaxCall(htmlType, urlAndId)
          .done(
            function(result) {

              console.log("Data loaded");

              // reset commentList
              commentListUl.empty();

              // console.log(result);

              $(result)
                .each(
                  function(index,
                    comment) {

                    /*
                     * set creating
                     * datetime in
                     * correct
                     * format
                     */
                    var date = new Date(
                      comment.created);

                    function addZero(
                      i) {
                      if (i < 10) {
                        i = "0" +
                          i;
                      }
                      return i;
                    }
                    var dateFormatted = (addZero(date
                        .getDate()) +
                      '-' +
                      addZero(date
                        .getMonth() + 1) +
                      '-' +
                      date
                      .getFullYear() +
                      '&nbsp;&nbsp;' +
                      addZero(date
                        .getHours()) +
                      ':' + addZero(date
                        .getMinutes()));

                    var listItem = $(`<li><b>${comment.user.firstName}&nbsp;${comment.user.lastName}&nbsp;(${dateFormatted}):</b><br>${comment.commentText}</li>`);

                    $(commentListUl)
                      .append(
                        listItem);
                  });

              $(commentListDiv).slideToggle(300);

            }).fail(function(statusText, e) {
            console.log("Error" + e);
            console.log(statusText);

          }).always(function() {
            console.log("End of connection");
          });
      });

  /*
   * add comment on click
   */
  var button = $(".tweet-submit-comment")
  button
    .on(
      "click",
      function(event) {

        var urlAndId = "add/" +
          $(event.currentTarget).data("tweet-id") + "/" +
          $(event.currentTarget).data("user-id");
        console.log(urlAndId);
        var htmlType = $(event.currentTarget).data("type");
        console.log(htmlType);

        var jsonData = JSON
          .stringify({
            "commentText": ($(
                event.currentTarget.parentElement.parentElement.parentElement)
              .find(
                "textarea[name='commentText']")
              .val())
          });
        console.log(jsonData);

        var commentListDiv = event.currentTarget.parentElement.nextElementSibling;

        ajaxCall(htmlType, urlAndId, jsonData).done(function() {
          console.log("Data saved");
          location.reload();
          //
          //
          // $(commentListDiv).show(300);

        }).fail(function(statusText, e) {
          console.log("Error " + e);
          console.log(statusText);

        }).always(function() {
          console.log("End of connection");
        });
      });

  function ajaxCall(htmlType, urlAndId, jsonData) {
    console.log("---ajax data start----");
    console.log(htmlType);
    console.log(urlAndId);
    console.log(jsonData);
    console.log("---ajax data end----");
    if (urlAndId === undefined) {
      urlAndId = "";
    }
    return $.ajax({
      type: htmlType,
      url: url + "comment/" + urlAndId,
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      data: jsonData,
      dataType: 'json'
    });

  };
});
