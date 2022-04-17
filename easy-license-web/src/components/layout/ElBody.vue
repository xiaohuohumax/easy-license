<template>
  <div class="h-screen flex flex-col">
    <div class="flex-shrink-0">
      <ElHeader />
    </div>
    <div class="flex flex-grow h-1">
      <div class="flex-shrink-0" style="width: 230px">
        <ElMenu />
      </div>
      <div class="flex-grow flex flex-col">
        <div class="flex-shrink-0">
          <ElHistory />
        </div>
        <div class="flex-grow overflow-y-auto">
          <ElContent />
        </div>
        <div class="flex-shrink-0">
          <ElFooter />
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { useMessage } from "naive-ui";
window.$message = useMessage();
</script>