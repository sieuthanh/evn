var Tab = { cur:0 };

function initab( name, w, h ) {
 name = name || 'tab';
 var tabs = [], divs = [], texts = [], f, g, i = 0, j = 0, k, t, o, p,
  d = document, a = d.getElementsByTagName( 'h3' ),
  b = d.createElement('div'), c = d.createElement('a'),
  r = new RegExp( '\\b' + name + '\\b' ),
  y = function(){return false;},
  z = function(e) { e = e || window.event || {}; var t;
   if( e.keyCode===37 &&
    ( ( t = this.previousSibling ) || ( t = this.parentNode.lastChild ) ) ||
    e.keyCode===39 &&
    ( ( t = this.nextSibling ) || ( t = this.parentNode.firstChild ) ) ) {
     if( /a/i.test( t.tagName ) ) { t.focus(); e.returnValue = false; }
     return false;
   }
  };
 b.className = 'tab' + name;
 c.className = 'tab0';
 c.hideFocus = true;
 while( i < a.length ) { p = a[i];
  if( r.test( p.className ) && ( g = getnext(p) ) ) {
   p.style.width =  ( w || 700 ) + 'px';
   p.style.height = ( h || 400 ) + 'px';
   p.className += ' slab0';
   g.className += ' slab0';t='jan';//alert(p.outerHTML);
   t = gettext( p );
   o = c.cloneNode( true );
   o.onclick = y;
   o.onfocus = new Function( 'return dotob(' + j + ');' );
   o.onkeydown = z;
   o.href = '?' + ( j + 1 );
   o.appendChild( d.createTextNode( t || 'Tab ' + (j+1) ) );
   b.appendChild(o);
   tabs[j] = o; divs[j] = g; texts[j] = t.toLowerCase();
   f = f || p;
   j++;
  }
  i++;
 }
 f.parentNode.insertBefore( b, f );
 Tab.tabs = tabs; Tab.divs = divs; Tab.texts = texts; i = 0;
 if( ( j = window.location.search.substring(1) ) ) {
  k = ( '. ' + texts.join(' ') + ' ' ).split( new RegExp( ' ' + j + ' ' ) )[0].split( /\s/ ).length-1;
  if( tabs[k] ) { i = k; }
  else if( ( j = parseInt( j, 10 ) - 1 ) && tabs[j] ) { i = j; }
 }
 dotob(i);
}

function untab( name ) {
 name = name || 'tab';
 var i, p, q,
  d = document, a = d.getElementsByTagName( 'h3' ),
  r = new RegExp( '\\b' + name + '\\b' );
 i = a.length; while(i--) { p = a[i]; q = p.className;
  if( r.test( q ) ) {
   p.className = q.replace( / slab\d/, '' );
( g = getnext(p) )
  }
 }
 a = d.getElementsByTagName( 'div' );
 r = new RegExp( '\\btab' + name + '\\b' );
 i = a.length; while(i--) { p = a[i];
  if( r.test( p.className ) ) {
   p.parentNode.removeChild(p);
  }
 }
}

function dotab(i) {
  Tab.tabs[Tab.cur].className = 'tab0';
  Tab.divs[Tab.cur].className = 'slab0';
  Tab.cur = i;
  Tab.tabs[i].className = 'tab1';
  Tab.divs[i].className = 'slab1';
  return false;
}

function dotob(i) {
 var r = /(t|sl)ab\d/, t = function(s) {
  Tab.tabs[Tab.cur].className = Tab.tabs[Tab.cur].className.replace( r, 'tab' + s );
  Tab.divs[Tab.cur].className = Tab.divs[Tab.cur].className.replace( r, 'slab' + s );
 };
 t( '0' ); Tab.cur = i; t( '1' );
 var obj=Tab.divs[i];
 if(obj!=null && obj.onclick!=null)
 {
   obj.onclick();
 }
 return false;
}

if( typeof gettext==='undefined' ) { function gettext( o ) { if(o) for( var s = '', j = o.firstChild; j !== null; j = j.nextSibling ) { if( j.nodeType===1 ) { s += gettext( j ); } else if( j.nodeType===3 ) { s += j.nodeValue || ' '; } } return s.replace( /\s+/, ' ' ); } }

if( typeof getnext==='undefined' ) { function getnext(o) { while( ( o = o.nextSibling ) ) { if( /div/i.test( o.tagName ) ) { return o; } if( o.tagName ) { return null; } } return null; } }
