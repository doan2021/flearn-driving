var pageListData = new Vue({
    el: '#app-list-data',
    data: {
        listDataCustomer: [],
        listHistory     : [],
        fileCsv         : [],
        messageError    : '',
        message         : ''
    },
    mounted() {
        this.getData(0);
        setTimeout(function(){
        	stopLoading();
        }, 1000);
    },
    methods: {
        getData: function(page){
        	startLoading();
        	var _this = this;
            var config = {
                url: "/get-list-data-customer", 
                method: "GET",
                params: {
                    pageCurrent : page
                }
            }
            axios(config).then(function (response) {
            	_this.listDataCustomer = response.data.result;
            	stopLoading();
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        previewFiles: function() {
        	this.fileCsv = this.$refs.fileCsv;
        },
        getHistory(customerId){
        	var _this = this;
            var config = {
                url: "/get-history-customer", 
                method: "GET",
                params: {
                	customerId : customerId
                }
            }
            axios(config).then(function (response) {
                _this.listHistory = response.data.result;
            })
            .catch(function (error) {
            	_this.messageError = error;
            });
        },
        importCsv: function() {
            startLoading();
            var _this = this;
            var formData = new FormData();
            formData.append('fileCsv', this.fileCsv.files[0]);
            axios.post("/import-data", formData, { headers : {
              'Content-Type': 'multipart/form-data'}
            }).then(function (response) {
            	stopLoading();
            })
            .catch(function (error) {
            	stopLoading();
            	_this.messageError = error;
            });
        }
    }
})