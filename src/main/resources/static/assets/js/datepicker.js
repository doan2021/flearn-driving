Vue.component('datepicker', {
    props: {
        id: {
            type: String,
            required: false
        },
        classValid: {
            type: String,
            required: false
        }
    },
    watch: {
        id: function(value) {
            this.idData = value;
        },
        classValid: function(value) {
            this.classValidData = value;
        },
        valueData: function(value) {
            this.$emit('input', value);
        }
    },
    data: function () {
        return {
            idData : this.id,
            classValidData : this.classValid,
            valueData: ''
        }
    },
    mounted() {
        var _this = this
        $('#' + this.idData).datepicker({
            changeMonth : true,
            changeYear : true,
            showButtonPanel : true,
            onSelect: function(dateText) {
               _this.valueData = dateText;
            }
        });
    },
    template:'<input type="text" class="form-control datepicker" v-model="valueData" :id="idData" :class="[classValidData]" placeholder="Chọn ngày" autocomplete="off"/>'
})