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

//-------Navigation bar collapse ------------
$(document).ready(function () {
    $("#sidebar").mCustomScrollbar({
        theme: "minimal"
    });



    $('#dismiss, .overlay').on('click', function () {
        $('#sidebar').removeClass('active');
        $('.overlay').removeClass('active');
    });

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').addClass('active');
        $('.overlay').addClass('active');
        $('.collapse.in').toggleClass('in');
        $('a[aria-expanded=true]').attr('aria-expanded', 'false');
    });
});
//---------------------------------------
