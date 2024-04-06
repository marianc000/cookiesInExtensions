const url='test';

function load(url,method='GET'){
  return  fetch(url,{method}).then(r => r.text()).then(console.log);
}

btn.addEventListener('click',e=>{
  load(url).then(()=>load(url,'POST')); 
});