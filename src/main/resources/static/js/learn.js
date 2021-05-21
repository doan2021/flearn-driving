var pageListData = new Vue({
    el: '#app-learn',
    data: {
        chapterId: $('#chapterId').val(),
        dataResult: '',
        resultAnswer: '',
        aswered     : false
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
                    $('#loading').fadeOut();
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        submitAnswer: function(answerId){
            $('#loading').fadeIn();
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
                    $('#loading').fadeOut();
                    if (_this.resultAnswer.status == true) {
                        alert(_this.resultAnswer.message);
                    } else {
                        alert(_this.resultAnswer.message);
                    }
                    _this.aswered = true;
                })
                .catch(function (error) {
                    $('#loading').fadeOut();
                    console.log(error);
                });
        }
    }
})