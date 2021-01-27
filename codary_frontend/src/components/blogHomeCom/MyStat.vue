<template>
<div class='py-12'>
    <strong>블로그 방문자 수</strong>
    <div class='py-12'></div>
    <v-row>
      <v-col>
         <v-sheet
          class="v-sheet--offset mx-auto"
          elevation="12"
          max-width="calc(100% - 32px)"
        >
          <v-sparkline
            :labels="labels"
            :value="value"
            line-width="2"
            padding="16"
          ></v-sparkline>
        </v-sheet>
        
      </v-col>
    </v-row>
</div>
  
</template>

<script>
  export default {
    data: () => ({
      labels: [
        '1/27',
        '1/27',
        '1/27',
        '1/27',
        '1/27',
        '1/27',
        '1/27',
        '1/27',
    
      ],
      value: [
        200,
        675,
        410,
        390,
        310,
        460,
        250,
        240,
      ],
    }),
  }
</script>

<style>

</style>