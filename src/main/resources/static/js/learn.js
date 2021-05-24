var pageListData = new Vue({
    el: '#app-learn',
    data: {
        chapterId: $('#chapterId').val(),
        dataResult: '',
        resultAnswer: '',
        rest       : 0,
        knowledge  : 0,
        familiar   : 0,
        statusLoadQuestion :'1R'
    },
    mounted() {
        this.initPage();
    },
    methods: {
        initPage(){
            $('#loading').fadeIn();
            var _this = this;
            var config = {
                    url: "/load-page-learn", 
                    method: "POST",
                    params: {
                        chapterId : this.chapterId
                    }
                }
                axios(config).then(function (response) {
                    console.log(response.data.result);
                    _this.dataResult = response.data.result;
                    _this.getStatusLearn();
                    $('#loading').fadeOut();
                })
                .catch(function (error) {
                    console.log(error);
                    $('#loading').fadeOut();
                });
        },
        submitAnswer: function(answerId){
            var _this = this;
            var config = {
                    url: "/submit-answer", 
                    method: "POST",
                    params: {
                        questionId : this.dataResult.questionRandom.questionId,
                        answerId : answerId
                    }
                }
                axios(config).then(function (response) {
                    _this.resultAnswer = response.data;
                    if (_this.resultAnswer.status == 'true') {
                        $.alert({
                            icon: 'far fa-laugh-squint',
                            theme: 'modern',
                            title: _this.resultAnswer.message,
                            content: 'Next the question ?',
                            type: 'green',
                            buttons: {
                                confirm: function () {
                                    _this.initPage();
                                }
                            }
                        });
                    } else {
                        $.alert({
                            icon: 'far fa-sad-cry',
                            theme: 'modern',
                            title: _this.resultAnswer.message,
                            content: 'Next the question ?',
                            type: 'red',
                            buttons: {
                                confirm: function () {
                                    _this.initPage();
                                }
                            }
                        });
                    }
                })
                .catch(function (error) {
                    $('#loading').fadeOut();
                    console.log(error);
                });
        },
        getStatusLearn(){
            var _this = this;
            var config = {
                    url: "/get-status-learn", 
                    method: "GET",
                    params: {
                        chapterId : this.chapterId
                    }
                }
                axios(config).then(function (response) {
                    _this.rest = response.data.result.rest;
                    _this.knowledge = response.data.result.knowledge;
                    _this.familiar = response.data.result.familiar;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})