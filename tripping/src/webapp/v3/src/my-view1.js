/**
 * @license
 * Copyright (c) 2016 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
 */

import { PolymerElement, html } from "@polymer/polymer/polymer-element.js";
import "./shared-styles.js";
import "./vendor/good-map.js";

class MyView1 extends PolymerElement {
  static get template() {
    return html`
      <style include="shared-styles">
        :host {
          display: block;
          padding: 10px;
        }
        good-map {
          display: block;
          height: 400px;
        }
      </style>

      <div class="card">
        <div class="circle">1</div>
        <h1>View One</h1>
        <p>Ut labores minimum atomorum pro. Laudem tibique ut has.</p>
        <good-map id="googleMap" api-key="AIzaSyB4z0tiyJJdQyi-Md0awocjBAryJjidVg0"
          latitude="30" longitude="-100" zoom="2"
          map-options='{"mapTypeId": "satellite"}'>
        </good-map>
        </div>
    `;
  }
  
  constructor() {
    super();
  }

  ready() {
    super.ready();

    this.internal = {
      mapAPI :this.$.googleMap,
      
    };

    this.internal.mapAPI.addEventListener("google-map-ready", this.onMapReady.bind(this));
  }

  onMapReady(event) {
    event.detail.addListener('click', this.onMapClick.bind(this));
  }

  onMapClick(event){

    var myLatLng = {lat: 30, lng: -100};

    var markerLbl = {
      text: '1',
      color: 'white',
      fontWeight: 'bold'
    };

    var marker = new google.maps.Marker({
      map: this.internal.mapAPI.map,
      position: myLatLng,
      draggable: true,
      label: markerLbl
    });
  }

}

window.customElements.define("my-view1", MyView1);
