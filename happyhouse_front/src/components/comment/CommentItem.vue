<template>
  <div>
    <div class="d-flex justify-content-center py-2">
      <div class="second py-2 px-2" style="margin-bottom: 2%">
        <span class="text">
          <div style="text-align: left">
            <img src="https://i.imgur.com/AgAC1Is.jpg" width="30" />
            <span style="font-size: 15px; font-weight: bold; text-align: left">
              작성자 | {{ comment.userid }}
            </span>
            <div style="text-align: right">
              <span style="font-size: 12px; float: right">
                {{ comment.regtime }}
              </span>
            </div>
          </div>
          <p
            class="lead"
            v-if="!isUpdate"
            style="text-align: left; font-size: 17px"
          >
            {{ comment.content }}
          </p>

          <b-form-input
            id="comment"
            v-model="updateContent"
            type="text"
            placeholder="내용 입력..."
            v-else
          ></b-form-input
        ></span>

        <div v-if="userCheck()">
          <div
            class="d-flex justify-content-between py-1 pt-2"
            style="float: right"
          >
            <div v-if="!isUpdate">
              <b-button type="default" size="sm" @click="updateToggle"
                >update</b-button
              >
              <b-button type="default" size="sm" @click="deleteComment"
                >delete</b-button
              >
            </div>
            <div v-else>
              <b-button type="default" size="sm" @click="updateComment"
                >confirm</b-button
              >
              <b-button type="default" size="sm" @click="updateToggle"
                >cancel</b-button
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "CommentItem",
  props: {
    comment: Object,
  },
  data() {
    return {
      isUpdate: false,
      updateContent: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    deleteComment() {
      this.$emit("deleteComment", this.comment.commentno);
    },
    updateToggle() {
      this.updateContent = this.comment.content;
      this.isUpdate = !this.isUpdate;
    },
    updateComment() {
      const updateComment = {
        commentno: this.comment.commentno,
        boardno: this.comment.boardno,
        userid: this.comment.userid,
        content: this.updateContent,
      };
      if (this.updateContent != this.comment.content) {
        this.$emit("updateComment", updateComment);
      }
      this.updateToggle();
    },
    userCheck() {
      //console.log(this.comment.userid, this.userInfo.userid);
      return this.comment.userid === this.userInfo.userid;
    },
  },
};
</script>

<style>
.addtxt {
  padding-top: 10px;
  padding-bottom: 10px;
  text-align: center;
  font-size: 13px;
  width: 350px;
  background-color: #e5e8ed;
  font-weight: 500;
}
.second {
  width: 100%;
  background-color: rgba(219, 219, 219, 0.671);
  border-radius: 4px;
  box-shadow: 10px 10px 5px #aaaaaa;
}
.text {
  font-size: 13px;
  font-weight: 500;
  color: #56575b;
}
</style>

<!-- 변경 전 -->
<!-- <template>
  <div style="outline-style: solid">
    <p class="lead" v-if="!isUpdate">
      {{ comment.content }}
    </p>
    <b-form-input
      id="comment"
      v-model="updateContent"
      type="text"
      placeholder="내용 입력..."
      v-else
    ></b-form-input>
    <span style="font-size: 14px">
      {{ comment.regtime }}
    </span>
    <span style="font-size: 14px; font-weight: bold">
      {{ comment.userid }}
    </span>
    <div v-if="!isUpdate">
      <b-button type="default" size="sm" @click="updateToggle">update</b-button>
      <b-button type="default" size="sm" @click="deleteComment"
        >delete</b-button
      >
    </div>
    <div v-else>
      <b-button type="default" size="sm" @click="updateComment"
        >confirm</b-button
      >
      <b-button type="default" size="sm" @click="updateToggle">cancel</b-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommentItem",
  props: {
    comment: Object,
  },
  data() {
    return {
      isUpdate: false,
      updateContent: "",
    };
  },
  methods: {
    deleteComment() {
      this.$emit("deleteComment", this.comment.commentno);
    },
    updateToggle() {
      this.updateContent = "";
      this.isUpdate = !this.isUpdate;
    },
    updateComment() {
      const updateComment = {
        commentno: this.comment.commentno,
        boardno: this.comment.boardno,
        userid: this.comment.userid,
        content: this.updateContent,
      };
      if (this.updateContent != "") {
        this.$emit("updateComment", updateComment);
      }
      this.updateToggle();
    },
  },
};
</script>

<style></style> -->
