const url='https://euphoric-patrol-419418.lm.r.appspot.com/test';

function load(url,method='GET'){
  return  fetch(url,{method}).then(r => r.text()).then(console.log);
}

chrome.action.onClicked.addListener(() => {
  load(url).then(()=>load(url,'POST')); 
});
