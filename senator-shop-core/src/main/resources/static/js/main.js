// var messagesApi = Vue.resource('/rest/getAll')
//
// Vue.component('message-row', {
//     props:['message'],
//     template:'<div> {{ message.id }} {{ message.productName }}</div>'
// })
//
// Vue.component('messages-list', {
//     props:['messages'],
//     template: '<div><message-row v-for="message in messages" :message="message" :key="message.id"/></div>',
//     created: function(){
//         messagesApi.get().then(result => result.data.forEach(element => this.messages.push(element)))
//     }
//   })
//
// var app = new Vue({
//     el: '#app',
//     template:'<messages-list :messages="messages"/>',
//     data: {
//       messages: []
//     }
//   })

'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

function uploadSingleFile(file, storage) {
    var formData = new FormData();
    formData.append("file", file);
    var storeloc = '';
    var xhr = new XMLHttpRequest();
    if(storage == 'filesys'){
        xhr.open("POST", "/uploadFile");
        storeloc = 'Filesystem';
    } else if (storage == 'database'){
        xhr.open("POST", "/uploadFileDB");
        storeloc = 'Database';
    }

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully to the " + storeloc + ".</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred while uploading to the " + storeloc;
        }
    }

    xhr.send(formData);
}

function uploadMultipleFiles(files, storage) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }
    var xhr = new XMLHttpRequest();
    var storeloc = '';
    if(storage == 'filesys'){
        xhr.open("POST", "/uploadMultipleFiles");
        storeloc = 'Filesystem';
    } else if (storage == 'database'){
        xhr.open("POST", "/uploadMultipleFilesDB");
        storeloc = 'Database';
    }

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            multipleFileUploadError.style.display = "none";
            var content = "<p>All Files Uploaded Successfully</p>";
            for(var i = 0; i < response.length; i++) {
                content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
            }
            multipleFileUploadSuccess.innerHTML = content;
            multipleFileUploadSuccess.style.display = "block";
        } else {
            multipleFileUploadSuccess.style.display = "none";
            multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred while uploading to the " + storeloc;
        }
    }

    xhr.send(formData);
}


singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    var storeloc;
    if (document.getElementById('rs1').checked)
        storeloc = document.getElementById('rs1').value;
    else
        storeloc = document.getElementById('rs2').value;

    uploadSingleFile(files[0], storeloc);
    event.preventDefault();
}, true);


multipleUploadForm.addEventListener('submit', function(event){
    var files = multipleFileUploadInput.files;
    if(files.length === 0) {
        multipleFileUploadError.innerHTML = "Please select at least one file";
        multipleFileUploadError.style.display = "block";
    }
    var storeloc;
    if (document.getElementById('rm1').checked)
        storeloc = document.getElementById('rm1').value;
    else
        storeloc = document.getElementById('rm2').value;
    uploadMultipleFiles(files, storeloc);
    event.preventDefault();
}, true);
