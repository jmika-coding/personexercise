function remove(route, route2){
console.log("We are here");
      fetch(route, {
        method: 'DELETE'
      }).then(() => {
         console.log('removed');
         fetch(route2, {
                 method: 'GET'
         }).then(() => { location.reload();
                        console.log("remove2")
                        })
      }).catch(err => {
        console.error(err)
      });
}

// Not working now
function update(route, route2){
      fetch(route, {
        method: 'PUT'
      }).then(() => {
         fetch(route2, {
                 method: 'GET'
         }).then(() => { location.reload();
                        })
      }).catch(err => {
        console.error(err)
      });
}
