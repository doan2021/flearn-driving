Vue.component('pagination', {
    props: {
        totalPages: {
            type: Number,
            required: false,
            default: 0
        },
        total: {
            type: Number,
            required: false,
            default: 0
        },
        currentPage: {
            type: Number,
            required: false,
            default: 0
        }
    },
    computed: {
        isInFirstPage() {
          return this.currentPage === 0;
        },
        isInLastPage() {
          return this.currentPage === this.totalPages - 1;
        },
    },
    methods: {
        onClickFirstPage() {
          this.$emit('pagechanged', 0);
        },
        onClickPreviousPage() {
          this.$emit('pagechanged', this.currentPage - 1);
        },
        onClickPage(page) {
          this.$emit('pagechanged', page);
        },
        onClickNextPage() {
          this.$emit('pagechanged', this.currentPage + 1);
        },
        onClickLastPage() {
          this.$emit('pagechanged', this.totalPages - 1);
        }
    },
    template:'<div v-if="total != 0" class="float-right">'
            +'    <button type="button" class="btn btn-social btn-fill btn-reddit" @click="onClickFirstPage" :disabled="isInFirstPage"><i class="fas fa-angle-double-left"></i></button>'
            +'    <button type="button" class="btn btn-social btn-fill btn-reddit" @click="onClickPreviousPage" :disabled="isInFirstPage"><i class="fas fa-angle-left"></i></button>'
            +'    <span>{{ currentPage }} / {{ totalPages - 1 }}</span>'
            +'    <button type="button" class="btn btn-social btn-fill btn-reddit" @click="onClickNextPage" :disabled="isInLastPage"><i class="fas fa-angle-right"></i></button>'
            +'    <button type="button" class="btn btn-social btn-fill btn-reddit" @click="onClickLastPage" :disabled="isInLastPage"><i class="fas fa-angle-double-right"></i></button>'
            +'</div>'
})