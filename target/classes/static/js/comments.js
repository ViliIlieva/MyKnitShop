
let commentSection = document.getElementById('comment')

fetch(`http://localhost:8080/api/comments`)
    .then((responsе) => responsе.json())
    .then(json => json.forEach(message => {
        let commentHtml = '<div class="comment">\n'
        commentHtml += `<p>${message.description}</p>\n`
        commentHtml += `<h4>${message.author.firstName}</h4>\n`
        commentHtml += '</div>\n'

        commentSection.innerHTML += commentHtml
    }))
    // .then((body) => {
    //     for (comment of body) {
    //         let commentHtml = '<div class="comment">\n'
    //         commentHtml += `<p>${comment.description}</p>\n`
    //         commentHtml += `<h4>${comment.author.firstNamame}</h4>\n`
    //         commentHtml += '</div>\n'
    //
    //         commentSection.innerHTML += commentHtml
    //     }
    // })