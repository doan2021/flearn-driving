var pageListData = new Vue({
    el: '#app-user-managerment',
    data: {

    },
    mounted() {
        this.getData(1);
        setTimeout(function(){
        	stopLoading();
        }, 1000);
    },
    methods: {
        getData: function(page){
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
                console.log(error);
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
            	_this.isError = true;
            	stopLoading();
            })
            .catch(function (error) {
            	stopLoading();
                console.log(error);
                _this.isError = false;
            });
        }
    }
})