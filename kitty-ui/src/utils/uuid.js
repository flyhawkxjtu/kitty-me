export default function uuid() {
    function uuid_s4() {
      return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (uuid_s4()+uuid_s4()+uuid_s4()+uuid_s4()+uuid_s4()+uuid_s4()+uuid_s4()+uuid_s4());
}